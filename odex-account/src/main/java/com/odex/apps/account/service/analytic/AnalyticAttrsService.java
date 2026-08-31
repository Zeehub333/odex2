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
package com.odex.apps.account.service.analytic;

import com.odex.apps.account.db.Account;
import com.odex.apps.account.db.repo.AnalyticLine;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.Company;
import com.odex.apps.base.db.Partner;
import com.odex.apps.base.db.Product;
import com.odex.apps.base.db.TradingName;
import java.util.Map;

public interface AnalyticAttrsService {

  void addAnalyticAxisAttrs(
      Company company, int massEntryStatusSelect, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void addAnalyticAxisAttrs(
      Company company, String parentField, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void addAnalyticAxisDomains(
      AnalyticLine analyticLine, Company company, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  String getAnalyticDistributionTemplateDomain(
      Partner partner,
      Product product,
      Company company,
      TradingName tradingName,
      Account account,
      boolean isPurchase)
      throws AxelorException;

  void addAnalyticAccountRequired(
      AnalyticLine analyticLine, Company company, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void addAnalyticDistributionTemplateDomain(
      AnalyticLine analyticLine,
      Partner partner,
      Product product,
      Company company,
      TradingName tradingName,
      Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;
}
