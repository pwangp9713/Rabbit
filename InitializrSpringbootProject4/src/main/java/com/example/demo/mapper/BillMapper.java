/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mapper;

import com.example.demo.domain.Bill;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author root
 */
@Mapper
public interface BillMapper {
    Bill checkbill(int id);
}
