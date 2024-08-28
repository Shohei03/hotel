package com.example.Hotel.domain.top;

import com.example.Hotel.web.hotelSite.SearchKeyword;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    /* ホテル情報全て取得 */
    public Page<HotelEntity> getHotelAll(Pageable pageable){
        RowBounds rowBounds = new RowBounds(
                (int)pageable.getOffset(), pageable.getPageSize());
        List<HotelEntity> hotels = hotelRepository.findAll(rowBounds);

        long total = hotelRepository.count();
        return new PageImpl<>(hotels, pageable, total);
    };

    /* トップ画面ホテル情報取得 */
    public List<HotelEntity> getHotelTop() {
        return hotelRepository.findTopInfo();
    }

    /* idで取得されたホテルレコード取得*/
    public HotelEntity getHotelById(Long id){
        return hotelRepository.findById(id);
    };

    /* ホテル名からホテル情報を取得*/
    public List<HotelEntity> getHotelByName(String name){
        return hotelRepository.findHotelByName(name);
    };

    /* ホテル名からホテル情報を取得（ページング用）*/
    public Page<HotelEntity> getHotelByNamePaging(SearchKeyword searchKeyword, String orderItem, Pageable pageable){
        RowBounds rowBounds = new RowBounds(
                (int)pageable.getOffset(), pageable.getPageSize());
        List<HotelEntity> hotels =  hotelRepository.findHotelBySearchKeyPaging(searchKeyword, orderItem, rowBounds);

        int total = hotelRepository.getCountBySearchword(searchKeyword);
        return new PageImpl<>(hotels, pageable, total);
    };


    /* ホテル名からホテル情報を取得（並び順指定あり）*/
    public List<HotelEntity> getHotelByNameByOrder(String name,String orderItem){
        return hotelRepository.findHotelByNameByOrder(name, orderItem);
    }

    /* ホテル名からホテル情報を取得（並び順指定あり） ページング*/
    public Page<HotelEntity> getHotelByNameByOrderPaging(SearchKeyword searchKeyword,String orderitem, Pageable pageable){
        RowBounds rowBounds = new RowBounds(
                (int)pageable.getOffset(), pageable.getPageSize());
        List<HotelEntity> hotels =  hotelRepository.findHotelBySearchKeyByOrderPaging(searchKeyword, orderitem, rowBounds);

        int total = hotelRepository.getCountBySearchword(searchKeyword);
        return new PageImpl<>(hotels, pageable, total);
    };

    /* エリア名からホテル情報を取得*/
    public List<HotelEntity> getHotelByArea(String area){
        return hotelRepository.findHotelByArea(area);
    };

    /* 予算からホテル情報を取得*/
    public List<HotelEntity> getHotelByBudget(String budget_lower,String budget_upper){
        return hotelRepository.findHotelByBudget(budget_lower, budget_upper);
    };

    /* 検索結果カウント*/
    public int getResultCount(SearchKeyword searchKeyword) {
        return hotelRepository.getCountBySearchword(searchKeyword);
    };

    public int getCountByBudget(String budget_lower,String budget_upper){
        return hotelRepository.getCountByBudget(budget_lower, budget_upper);
    }

    public int insert(HotelEntity hotel){
        return hotelRepository.insert(hotel);
    };

    /* ホテル情報削除 */
    public int deleteById(String id){
        return hotelRepository.delete(id);
    };

    /* ホテル情報更新 */
    public int updateHotel(Long id,HotelEntity hotel){
        hotel.setId(id);
        System.out.println("price___" + hotel.getPrice());
        return hotelRepository.update(hotel);

    };

    /* テスト_ページング*/
    public Page<HotelEntity> selectAll(Pageable pageable){
        RowBounds rowBounds = new RowBounds(
                (int)pageable.getOffset(), pageable.getPageSize());
        List<HotelEntity> hotels =  hotelRepository.selectAll(rowBounds);

        Long total = hotelRepository.count();
        return new PageImpl<>(hotels, pageable, total);
    };

    /* ログインユーザが予約しているホテル情報を取得（予約日は本日以降）*/
    public List<HotelEntity> getReserveHotel;


}
