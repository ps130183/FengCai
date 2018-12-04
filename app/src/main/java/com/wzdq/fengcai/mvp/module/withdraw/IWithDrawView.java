package com.wzdq.fengcai.mvp.module.withdraw;

import com.wzdq.fengcai.dto.WithDrawAccountDto;
import com.wzdq.fengcai.mvp.base.IBaseView;

import java.util.List;

/**
 * Created by PengSong on 18/1/25.
 */

public interface IWithDrawView extends IBaseView {
//    void showBalance(UserBalanceDto userBalanceDto);
    void withdrawSuccess(String money);

    void creatOrUpdateSuccess();
    void showWithDrawList(List<WithDrawAccountDto> withDrawAccountDtos);
    void deleteSuccess(WithDrawAccountDto withDrawAccountDto);
}
