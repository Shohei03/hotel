package com.example.Hotel.domain.top;
import com.example.Hotel.web.hotelSite.SearchKeyword;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface HotelService {
    /* ホテル情報全て取得 */
    Page<HotelEntity> getHotelAll(Pageable pageable);

    /* トップ画面ホテル情報取得 */
    List<HotelEntity> getHotelTop();

    /* idで取得されたホテルレコード取得*/
    HotelEntity getHotelById(Long id);

    /* ホテル名からホテル情報を取得*/
    List<HotelEntity> getHotelByName(String name);

    /* ホテル名からホテル情報を取得（ページング用）*/
    Page<HotelEntity> getHotelByNameByOrderPaging(SearchKeyword searchKeyword,String orderItem, Pageable pageable);
    Page<HotelEntity> getHotelByNamePaging(SearchKeyword searchKeyword, String orderItem, Pageable pageable);

    List<HotelEntity> getHotelByNameByOrder(String keyword,String orderItem);

    //Page<HotelEntity> getHotelByNameByOrderPaging(String keyword,String orderitem,Pageable pageable);

    /* エリアからホテル情報を取得*/
    List<HotelEntity> getHotelByArea(String area);

    /* 予算からホテル情報を取得*/
    List<HotelEntity> getHotelByBudget(String budget_lower,String budget_upper);

    /* 検索結果カウント*/
    int getResultCount(SearchKeyword searchKeyword);

    int getCountByBudget(String budget_lower,String budget_upper);


    /* テスト_ページング*/
    Page<HotelEntity> selectAll(Pageable pageable);

    /* ホテル情報登録 */
    int insert(HotelEntity hotel);

    /* ホテル情報削除 */
    int deleteById(String id);

    /* ホテル情報更新 */
    int updateHotel(Long id,HotelEntity hotel);

}
