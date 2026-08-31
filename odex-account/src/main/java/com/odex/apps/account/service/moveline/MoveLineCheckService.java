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
package com.odex.apps.account.service.moveline;

import com.odex.apps.account.db.Move;
import com.odex.apps.account.db.MoveLine;
import com.odex.apps.account.db.TaxLine;
import com.odex.apps.base.AxelorException;
import java.util.Set;

public interface MoveLineCheckService {
  void checkAnalyticByTemplate(MoveLine moveLine) throws AxelorException;

  void checkDebitCredit(MoveLine moveLine) throws AxelorException;

  void checkDates(Move move) throws AxelorException;

  void checkAnalyticMoveLinesPercentage(MoveLine moveLine) throws AxelorException;

  void nonDeductibleTaxAuthorized(Move move, MoveLine moveLine) throws AxelorException;

  void checkMoveLineTaxes(Set<TaxLine> taxLineSet) throws AxelorException;
}
