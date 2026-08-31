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
package com.odex.apps.account.service.period;

import com.odex.apps.account.db.AccountConfig;
import com.odex.apps.account.db.Move;
import com.odex.apps.account.db.repo.MoveRepository;
import com.odex.apps.account.service.config.AccountConfigService;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.Period;
import com.odex.apps.base.db.repo.PeriodRepository;
import com.odex.apps.base.service.user.UserRoleToolService;
import com.axelor.auth.db.User;
import jakarta.inject.Inject;

public class PeriodCheckServiceImpl implements PeriodCheckService {

  protected AccountConfigService accountConfigService;

  @Inject
  public PeriodCheckServiceImpl(AccountConfigService accountConfigService) {
    this.accountConfigService = accountConfigService;
  }

  @Override
  public boolean isAuthorizedToAccountOnPeriod(Period period, User user) throws AxelorException {
    if (period != null && period.getYear().getCompany() != null && user != null) {
      if (period.getStatusSelect() == PeriodRepository.STATUS_CLOSED) {
        return false;
      }
      if (period.getStatusSelect() == PeriodRepository.STATUS_TEMPORARILY_CLOSED) {
        AccountConfig accountConfig =
            accountConfigService.getAccountConfig(period.getYear().getCompany());
        return UserRoleToolService.checkUserRolesPermissionExcludingEmpty(
            user, accountConfig.getMoveOnTempClosureAuthorizedRoleList());
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean isAuthorizedToAccountOnPeriod(Move move, User user) throws AxelorException {
    if (move.getCompany() == null
        || move.getFunctionalOriginSelect() == MoveRepository.FUNCTIONAL_ORIGIN_OPENING
        || move.getFunctionalOriginSelect() == MoveRepository.FUNCTIONAL_ORIGIN_CLOSURE) {
      return true;
    }

    return isAuthorizedToAccountOnPeriod(move.getPeriod(), user);
  }
}
