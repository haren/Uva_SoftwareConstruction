QL & QLS
========

*Carlos Cirello - MSc. Software Engineering - Batch 2015/2016*

Questionaire Language is a toy project for UvA Software Construction Course 2014/2015 Batch. It is a DSL meant to describe forms (QL) and another DSL to describe their style (QLS).


# How to install?

This project depends on go-qml to compile execute, there for QT5 is expected to be available in the machine. Please, follow these [installation instructions](https://github.com/go-qml/qml/blob/v1/README.md#installation).

Once QT5 is available, execute:

```
$ git clone https://github.com/software-engineering-amsterdam/many-ql.git
$ cd many-ql/carlos.cirello
$ go build
$ ./carlos.cirello

or

C:\...> carlos.cirello
```

Or if you are in Unix-derived environment:
```
$ git clone https://github.com/software-engineering-amsterdam/many-ql.git
$ cd many-ql/carlos.cirello
$ make
$ ./carlos.cirello
```

# Documentation

## Inline documentation

Inline documentation is automatically generated by [godoc](https://godoc.org/golang.org/x/tools/cmd/godoc). Makefile has a target to shortcut its execution. On MacOS, it should open the browser automatically, otherwise point to the address it returns on console.

To access the inline documentation, execute:
```
$ make doc
```

## Slides

Some slides explaining few important decisions are available, and you should see them. It is incomplete, yet useful.

To access the slides, execute:
```
$ make talk
```

## TODO

This toy project is an ongoing effort. Therefore, it is expected to have lots of things to do. In order to extract the inline "to do" tasks, execute:
```
$ make todo
```

# Development Cycle

## Tests

Ideally, all relevant code should be tested. This project uses the native test system of Go.

If you want to run tests only:
```
$ make test
```

If you want to run tests with code coverage (incomplete):
```
$ make coverage
```

If you want it to detect changes, and automatically execute the tests (Red-Green-Refactor cycle):
```
$ make autotest
```