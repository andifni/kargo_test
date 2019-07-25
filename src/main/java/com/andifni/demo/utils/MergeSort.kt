package com.andifni.demo.utils

class MergeSort<T>(val list: ArrayList<T>, val comparator: Comparator<T>) {

    private fun merge(list1: ArrayList<T>, list2: ArrayList<T>): ArrayList<T> {
        var i = 0
        var j = 0
        val newArray = ArrayList<T>()
        while (i < list1.size && j < list2.size) {
            if (comparator.compare(list1[i], list2[j]) < 0) {
                newArray.add(list1[i])
                i++
            } else {
                newArray.add(list2[j])
                j++
            }
        }
        while (i < list1.size) {
            newArray.add(list1[i])
            i++
        }
        while (j < list2.size) {
            newArray.add(list2[j])
            j++
        }

        return newArray

    }

    private fun sort(l: ArrayList<T>): ArrayList<T> {
        if (l.size <= 1) return l
        val mid = l.size / 2
        val list1 = ArrayList<T>()
        for (i in 0 until mid) {
            list1.add(l[i])
        }
        val list2 = ArrayList<T>()
        for (i in mid until l.size) {
            list2.add(l[i])
        }
        return merge(sort(list1), sort(list2))
    }

    fun sort(): ArrayList<T> {
        return sort(this.list)
    }

}