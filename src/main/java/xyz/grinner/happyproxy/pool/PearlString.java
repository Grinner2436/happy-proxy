package xyz.grinner.happyproxy.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PearlString<K,P> {

    private Pearl<K,P> firstInString;
    private Pearl<K,P> lastInString;
    private HashMap<K,Pearl<K,P>> tails = new HashMap<>();
    private HashMap<K,Pearl<K,P>> heads = new HashMap<>();
    private HashMap<P,Pearl<K,P>> pearls = new HashMap<>();

    public boolean hasPearl(P p){
        return pearls.containsKey(p);
    }

    public void stringNewPearl(P p){
        Pearl<K,P> oldFirst = firstInString;
        Pearl<K,P> newPearl = new Pearl<>(p,firstInString);
        if(oldFirst == null){
            newPearl.nextInString = newPearl;
            lastInString = newPearl;
        }else{
            lastInString.nextInString = newPearl;
        }
        firstInString = newPearl;
        pearls.put(p,newPearl);
    }

    public void stringNewPearl(K k,P p){
        Pearl<K,P> oldFirst = firstInString;
        if(oldFirst == null){
            Pearl<K,P> newPearl = new Pearl<>(p,null);
            newPearl.stringNewPearl(k,newPearl);
            heads.put(k,newPearl);
            tails.put(k,newPearl);

            newPearl.nextInString = newPearl;
            lastInString = newPearl;
            firstInString = newPearl;
        }else{
            Pearl<K,P> headOfK = heads.get(k);
            if(headOfK == null){
                Pearl<K,P> newPearl = new Pearl<>(p,null);
                newPearl.stringNewPearl(k,newPearl);
                heads.put(k,newPearl);
                tails.put(k,newPearl);
                lastInString.nextInString = newPearl;
                firstInString = newPearl;
                pearls.put(p,newPearl);
            }else{
                Pearl<K,P> newPearl = new Pearl<>(p,headOfK);
                newPearl.stringNewPearl(k,headOfK);
                Pearl<K,P> tailsOfK = tails.get(k);
                tailsOfK.stringNewPearl(k,newPearl);
                lastInString.nextInString = newPearl;
                firstInString = newPearl;
                pearls.put(p,newPearl);
            }
        }
    }

    public P getHead(K k){
        Pearl<K,P> headOfK = heads.get(k);
        Pearl<K,P> tailsOfK = tails.get(k);
        if(headOfK == null){
            if(firstInString == null){
                return null;
            }
            firstInString.stringNewPearl(k,firstInString);
            heads.put(k,firstInString);
            tails.put(k,firstInString);
            return firstInString.item;
        }else{
            Pearl<K,P> currentChecking = firstInString;
            Pearl<K,P> subStringNext = firstInString.getNextOf(k);
            Pearl<K,P> nextToCheck = firstInString.nextInString;
            while (subStringNext != null && nextToCheck != firstInString){
                currentChecking = nextToCheck;
                subStringNext = nextToCheck.getNextOf(k);
                nextToCheck = nextToCheck.nextInString;
            }
            if(subStringNext == null){
                currentChecking.stringNewPearl(k,headOfK);
                tailsOfK.stringNewPearl(k,currentChecking);
                heads.put(k,currentChecking);
                return currentChecking.item;
            }else{
                Pearl<K, P> secondOfK = headOfK.getNextOf(k);
                heads.put(k,secondOfK);
                tails.put(k,headOfK);
                return headOfK.item;
            }
        }
    }

    public List<P> getList(){
        List<P> result = new ArrayList<>();
        Pearl<K,P> head = firstInString;
        if(head != null){
            result.add(head.item);
            Pearl<K,P> currentChecking = firstInString.nextInString;
            while (currentChecking != null && currentChecking != firstInString){
                result.add(currentChecking.item);
                currentChecking = currentChecking.nextInString;
            }
        }
        return result;
    }

    public List<P> getListOf(K k){
        List<P> result = new ArrayList<>();
        Pearl<K,P> headOfK = heads.get(k);
        if(headOfK != null){
            result.add(headOfK.item);
            Pearl<K,P> currentChecking = headOfK.getNextOf(k);
            while (currentChecking != null && currentChecking != headOfK){
                result.add(currentChecking.item);
                currentChecking = currentChecking.getNextOf(k);
            }
        }
        return result;
    }

    private static class Pearl<K,P> {
        private P item;
        private Pearl<K,P> nextInString;
        private HashMap<K,Pearl<K,P>> tassels = new HashMap<>();

        public Pearl(P item, Pearl<K,P> nextInString) {
            this.item = item;
            this.nextInString = nextInString;
        }

        public void stringNewPearl(K k,Pearl<K,P> pearl){
            tassels.put(k,pearl);
        }

        public Pearl<K, P> getNextOf(K k){
            return tassels.get(k);
        }
    }
}
