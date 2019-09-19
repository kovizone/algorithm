package com.kovizone.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 两数相加
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author KoviChen
 * @version 0.0.1 20190919 KoviChen 新建类
 */
public class Algorithm0009_LeetCode0001_AddTwoNumbers {

    @Test
    public void test() {
        ListNode l1 = new ListNode(6, 4, 8);
        ListNode l2 = new ListNode(5, 6, 4);
        System.out.println(addTwoNumbers2(l1, l2));
    }

    // 正序
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode curr = result;
        while (l1 != null || l2 != null) {
            int pVal = l1 == null ? 0 : l1.val;
            int qVal = l2 == null ? 0 : l2.val;

            int sum = pVal + qVal;
            curr.val += sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return result.val == 1 ? result : result.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode curr = result;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int pVal = l1 == null ? 0 : l1.val;
            int qVal = l2 == null ? 0 : l2.val;

            int sum = pVal + qVal + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        curr.next = carry > 0 ? new ListNode(1) : null;
        return result.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int... val) {
        this.val = val[0];
        ListNode listNode = this;
        for (int i = 1; i < val.length; i++) {
            listNode.next = new ListNode(val[i]);
            listNode = listNode.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.valueOf(this.val));
        ListNode ln = this.next;
        while (ln != null) {
            sb.append(" -> " + ln.val);
            ln = ln.next;
        }
        return sb.toString();
    }
}
