package com.example.Hotel.domain.top;

import com.example.Hotel.domain.payment.PaymentInfoEntity;
import com.example.Hotel.web.hotelSite.SearchKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface HotelRepository {
    /* ホテル情報すべて取得（ページングあり） */
    public List<HotelEntity> findAll(RowBounds rowBounds);

    /* トップ画面　登録日順にホテル情報を10件取得 */
    public List<HotelEntity> findTopInfo();

    /* idで取得されたホテルレコード取得*/
    public HotelEntity findById(Long id);

    /* ホテル名からホテル情報を取得*/
    public List<HotelEntity> findHotelByName(String name);

    public List<HotelEntity> findHotelByNamePaging(String name, RowBounds rowBounds);

    public List<HotelEntity> findHotelBySearchKeyPaging(@Param("searchKeyword") SearchKeyword searchKeyword,@Param("orderitem") String orderitem, RowBounds rowBounds);

    public List<HotelEntity> findHotelBySearchKeyByOrderPaging(@Param("searchKeyword") SearchKeyword searchKeyword, String orderitem, RowBounds rowBounds);

    /* ホテル名からホテル情報を取得（並び順指定有） ページング無し*/
    public List<HotelEntity> findHotelByNameByOrder(@Param("name") String name,@Param("orderItem") String orderItem);


    /* ホテル名からホテル情報を取得（並び順指定有） ページング*/
    public List<HotelEntity> findHotelByNameByOrderPaging(@Param("name") String name,@Param("orderItem") String orderItem, RowBounds rowBounds);


    /* エリアからホテル情報を取得*/
    public List<HotelEntity> findHotelByArea(String area);

    /* 予算からホテル情報を取得*/
    public List<HotelEntity> findHotelByBudget(String budget_lower,String budget_upper);

    /* 検索結果の件数を取得*/
    public int getCountBySearchword(@Param("searchKeyword") SearchKeyword searchKeyword);

    public int getCountByword(@Param("word") String word,@Param("key") int key);
    public int getCountByBudget(@Param("budget_lower") String budget_lower,@Param("budget_upper") String budget_upper);

    /* テスト_ページング*/
    public List<HotelEntity> selectAll(RowBounds rowBounds);

    /* 全件検索カウント用*/
    public Long count();

    /* ホテル情報新規登録*/
    public int insert(HotelEntity hotel);

    /* ホテル削除 */
    public int delete(String id);

    /* ホテル情報更新 */
    public int update(HotelEntity hotel);


}
