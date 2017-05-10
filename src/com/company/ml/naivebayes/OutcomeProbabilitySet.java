package com.company.ml.naivebayes;

/**
 * Created by AKEJU  FATAI on 2017-04-28.
 */
public class OutcomeProbabilitySet {

    private OutcomeProbability[] items;
    private int currentIndex;
    private int capacity;

    public OutcomeProbabilitySet(int capacity){

        currentIndex = -1;
        items = new OutcomeProbability[capacity];
        this.capacity = capacity;

    }

    public void add(OutcomeProbability outcomeProbability) throws Exception {

        if(isFull()){
            throw new Exception();
        }
        items[currentIndex+1] = outcomeProbability;

    }

    public OutcomeProbability getItem(int index){

        if(index <= currentIndex){
            return items[index];
        }
        throw new IndexOutOfBoundsException();

    }

    public boolean isFull(){

        if(currentIndex == (capacity - 1)){
            return true;
        }
        return false;

    }

    public int getCapacity(){

        return capacity;

    }

    public int getSize(){

        return (currentIndex + 1);

    }

    public OutcomeProbability getOutcomeWithMaximumProbability(){

        OutcomeProbability currentMaximum = items[0];
        for(int index = 1; index <= currentIndex; index++){
            if(currentMaximum.getProbability() < items[index].getProbability()){
                currentMaximum = items[index];
            }
        }
        return currentMaximum;

    }

}
