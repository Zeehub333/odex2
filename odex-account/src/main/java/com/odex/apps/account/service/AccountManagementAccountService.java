/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2026 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.odex.apps.account.service;

import com.odex.apps.account.db.Account;
import com.odex.apps.account.db.AccountManagement;
import com.odex.apps.account.db.AnalyticDistributionTemplate;
import com.odex.apps.account.db.FiscalPosition;
import com.odex.apps.account.db.FixedAssetCategory;
import com.odex.apps.account.db.Journal;
import com.odex.apps.account.db.PaymentMode;
import com.odex.apps.account.db.Tax;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.Company;
import com.odex.apps.base.db.Product;
import com.odex.apps.base.service.tax.AccountManagementService;
import java.util.List;

public interface AccountManagementAccountService extends AccountManagementService {

  /**
   * Get the product tax according to the fiscal position
   *
   * @param product
   * @param company
   * @param fiscalPosition
   * @param isPurchase Specify if we want get the tax for purchase or sale
   * @param fixedAsset Specify if we should get the purchase account for fixed asset or not. Used
   *     only if isPurchase param is true.
   * @return the tax defined for the product, according to the fiscal position
   * @throws AxelorException
   */
  public Account getProductAccount(
      Product product,
      Company company,
      FiscalPosition fiscalPosition,
      boolean isPurchase,
      boolean fixedAsset)
      throws AxelorException;

  /**
   * Get the product analytic distribution template
   *
   * @param product
   * @param company
   * @return
   * @throws AxelorException
   */
  public AnalyticDistributionTemplate getAnalyticDistributionTemplate(
      Product product, Company company, boolean isPurchase) throws AxelorException;

  /**
   * Get the product fixed asset category
   *
   * @param product
   * @param company
   * @return
   * @throws AxelorException
   */
  public FixedAssetCategory getProductFixedAssetCategory(Product product, Company company);

  public Account getCashAccount(AccountManagement accountManagement, PaymentMode paymentMode)
      throws AxelorException;

  public Account getPurchVatRegulationAccount(
      AccountManagement accountManagement, Tax tax, Company company) throws AxelorException;

  public Account getSaleVatRegulationAccount(
      AccountManagement accountManagement, Tax tax, Company company) throws AxelorException;

  public Account getTaxAccount(
      AccountManagement accountManagement,
      Tax tax,
      Company company,
      Journal journal,
      Account originalAccount,
      int vatSystemSelect,
      int functionalOrigin,
      boolean isFixedAssets)
      throws AxelorException;

  boolean areAllAccountsOfType(List<Account> accountList, String type);

  List<Account> getAccountsBetween(Account accountFrom, Account accountTo);
}
