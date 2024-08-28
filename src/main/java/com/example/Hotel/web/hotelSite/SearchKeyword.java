package com.example.Hotel.web.hotelSite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchKeyword {
    private String keyword;  // ホテル名
    private String area;     // 県
    private int budget_lower;
    private int budget_upper;
}
