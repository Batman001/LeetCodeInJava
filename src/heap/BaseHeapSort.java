package heap;

/**
 * @author Batman on 2018/12/19.
 * @author Batman
 */

import java.util.List;

/**
 * 基础的堆排序抽象类
 * @param <E>
 * @author Batman
 */
public abstract class BaseHeapSort<E> {
    /**
     * value1小于value2则返回true
     * @param value1
     * @param value2
     * @return
     */
    public abstract boolean compare(E value1, E value2);

    public boolean heapSort(List<E> list){//排序
        return heapSort(list, list.size());
    }


    public boolean heapSort(List<E> list, int n){
        if(null == list || 0 == list.size()){
            return false;
        }
        if(!heapCreate(list, n)){
            return false;
        }
        for(int i = n; i > 0; --i){
            swap(list, 0, i - 1);
            heapAdjust(list, 0, i - 1);
        }
        return true;
    }

    /**
     * 建立堆的过程
     * @param list
     * @param length
     * @return
     */
    public boolean heapCreate(List<E> list, int length){ //创建小根堆
        if(null == list || 0 == list.size()){
            return false;
        }
        for(int i = (length / 2 - 1); i >= 0; --i){
            if(!heapAdjust(list, i, length)){
                return false;
            }
        }
        return true;
    }


    /**
     * 调整堆，使其满足小根堆的条件
     * @param list
     * @param middle
     * @param length
     * @return
     */
    public boolean heapAdjust(List<E> list, int middle, int length){
        if(null == list || 0 == list.size()){
            return false;
        }
        E temp = list.get(middle);
        for(int i = (2 * middle + 1); i < length; i *= 2){
            if(i < (length - 1) && !this.compare(list.get(i), list.get(i + 1))){
                ++i;
            }
            if(this.compare(temp,list.get(i))){
                break;
            }
            list.set(middle, list.get(i));
            middle = i;
        }
        list.set(middle, temp);
        return true;
    }

    /**
     * 数据交换 交换列表中索引为i和j位置的值
     * @param list
     * @param i
     * @param j
     */
    public void swap(List<E> list, int i, int j){
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
