/**
 * The tax Office form 2.
 * Test the logical statements.
 */
form taxOfficeExample {
  bool hasSoldHouse("Did you sell a house in 2010?");
  bool hasBoughtHouse("Did you buy a house in 2010?");
  bool hasMaintLoan("Did you enter a loan?");
  int age("How old are you?");

  // If a house has been sold.
  if (hasSoldHouse || (age > 15 && age <= 55)) {
    money sellingPrice ("What was the selling price?");
    money privateDebt ("Private debts for the sold house:");
    money valueResidue ("Value residue:") = (sellingPrice * privateDebt - 77);
  }
}