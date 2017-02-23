/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cirro.jsonjoin.core;

import com.cirro.jsonjoin.entity.RowA;
import com.cirro.jsonjoin.entity.RowB;
import com.cirro.jsonjoin.entity.ResponseRow;
import com.cirro.jsonjoin.utils.Sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

/**
 *
 * @author alexander
 */
@Getter
@Setter
public class Processor {
    
    private List<RowA> streamA;
    private List<RowB> streamB;
    private List<ResponseRow> result;
    
    public Processor(Stream<RowA> streamA, Stream<RowB> streamB){
        this.streamA = streamA.collect(Collectors.toList());
        this.streamB = streamB.collect(Collectors.toList());
        result = new ArrayList<>();
    }
    
    public void start(){
        
        //Filtered by z > 0
        streamA.stream().filter(rA -> rA.getZ() > 20)
        
        //Join streamA and streamB on z=z
        .forEach( rA -> {
            streamB.stream().filter( rB -> matchZ (rA, rB) )
                    .forEach( rB -> addToResponse(rA, rB));
        });        
        
        //Group by X
        Map<Double, ResponseRow> counting = result.stream().collect(groupingBy(ResponseRow::getT1x, 
                        collectingAndThen(reducing((a, b) -> new ResponseRow(a, b)),
                                Optional::get
                        )
                )
        );
        
        // Assign the filtered values to response list
        result = new ArrayList<>();
        counting.forEach((x, item) -> {
                    result.add(item);
                });
        
        // Sort the final value
        Collections.sort(result, new Sorter());
    }
    
    private boolean matchZ(RowA rA, RowB rB) {
        return rA.getZ()==rB.getZ();
    }
    
    private void addToResponse(RowA rA, RowB rB) {
        result.add(new ResponseRow(rA.getX(), rA.getY(), rB.getY()));
    }
    
}
