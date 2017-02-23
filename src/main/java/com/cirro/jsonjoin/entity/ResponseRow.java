/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cirro.jsonjoin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexander
 */
@Getter
@Setter
public class ResponseRow {
    private double t1x;
    private double t1Y;
    private double t2Y;
    
    public ResponseRow(Double t1x, Double t1Y, Double t2Y) {
        this.t1x = t1x;
        this.t1Y = t1Y;
        this.t2Y = t2Y;
    }
    
    public ResponseRow(ResponseRow a, ResponseRow b){
        this.t1x = a.getT1x();
        this.t1Y = a.getT1Y() + b.getT1Y();
        this.t2Y = a.getT2Y() + b.getT2Y();
    }
}
