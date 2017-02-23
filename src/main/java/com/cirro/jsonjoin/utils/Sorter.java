/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cirro.jsonjoin.utils;

import com.cirro.jsonjoin.entity.ResponseRow;
import java.util.Comparator;

/**
 *
 * @author alexander
 */
public class Sorter  implements Comparator<ResponseRow>{

    @Override
    public int compare(ResponseRow x, ResponseRow y) {        
        if (x.getT2Y() < y.getT2Y())
            return 1;
        if (x.getT2Y() > y.getT2Y())
            return -1;
        else
            return 0;
    }
}
