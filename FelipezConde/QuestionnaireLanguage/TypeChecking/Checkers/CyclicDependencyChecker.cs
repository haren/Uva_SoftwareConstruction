﻿using AST.Nodes.Expressions;
using AST.Nodes.FormObjects;
using AST.VisitorInterfaces;
using Notifications;
using System.Collections.Generic;
using TypeChecking.Collectors;
using TypeChecking.Notifications.Errors;

namespace TypeChecking.Checkers
{
    public class CyclicDependencyChecker : Checker, IFormObjectVisitor<DependencyGraph>
    {
        public CyclicDependencyChecker(INotificationManager currentState) : base(currentState) { }
        public INotificationManager AnalyzeAndReport(IList<FormObject> body)
        {
            NotificationManager notificationManager = new NotificationManager();

            if (preconditions.HasError()) //check if preconditions have been met
                return notificationManager;

            foreach (Id key in InitializeDependencyGraph(body).GetCycles())
            {
                notificationManager.AddNotification(new CyclicDependency(key));
            }

            return notificationManager;
        }

        private DependencyGraph InitializeDependencyGraph(IList<FormObject> body)
        {
            DependencyGraph dependencyGraph = new DependencyGraph();

            foreach (FormObject formObject in body)
            {
                dependencyGraph.Combine(formObject.Accept(this));
            }

            return dependencyGraph;
        }

        public DependencyGraph Visit(Conditional conditional)
        {
            return new DependencyGraph();
        }

        public DependencyGraph Visit(Question question)
        {
            DependencyGraph dependencyGraph = new DependencyGraph();

            if (question.Computation != null)
            {
                dependencyGraph.AddNewDirectDepencencies(
                    question.Identifier, 
                    question.Computation.Accept(new UsedIdentifierCollector()));
            }

            return dependencyGraph;
        }
    }
}
