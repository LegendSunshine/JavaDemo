package com.leetcode.question2;

/**
 * @className App2
 * @description:
 * @author legend
 * @date 2025/2/21 20:32
 * @version 1.0
 */
public class App2 {
    public static void main(String[] args) {
        App2 app2 = new App2();
        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode = app2.addTwoNumbers(listNode1, listNode2);
        System.out.println(listNode);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode();
        //定义两个节点的值
        int firstVal = l1.val;
        int secondVal = l2.val;
        //两个节点相加 获取商和余数
        int sum = firstVal + secondVal;
        int carryNumber = sum / 10;
        int resultNumber = sum % 10;
        //余数作为当前节点的值,商作为进位
        headNode.val = resultNumber;
        if (l1.next != null || l2.next != null) {
            //如果两个节点有一个不为空则需要创建下一个节点
            headNode.next = new ListNode();
        }else {
            //否则结束累加
            if(carryNumber>0){
                //如果进位不为0 则创建一个节点
                headNode.next = new ListNode(carryNumber);
            }
            return headNode;
        }
        ListNode currentNode = headNode.next;
        ListNode l1Node = l1.next;
        ListNode l2Node = l2.next;
        //两个链表有一个不为空则继续循环
        while (l1Node != null || l2Node != null) {
            firstVal = 0;
            if (l1Node != null) {
                firstVal = l1Node.val;
                //如果下一个节点不为空则创建一个节点
                if(l1Node.next != null ){
                    currentNode.next = new ListNode();
                }
            }
            secondVal = 0;
            if (l2Node != null) {
                secondVal = l2Node.val;
                //如果下一个节点不为空则创建一个节点
                if(l2Node.next != null ){
                    currentNode.next = new ListNode();
                }
            }
            sum = firstVal + secondVal + carryNumber;
            carryNumber = sum / 10;
            resultNumber = sum % 10;
            currentNode.val = resultNumber;
            if(carryNumber>0 && currentNode.next == null) {
                currentNode.next = new ListNode(carryNumber);
            }
            currentNode = currentNode.next;
            if (l1Node != null) {
                l1Node = l1Node.next;
            }
            if (l2Node != null) {
                l2Node = l2Node.next;
            }
        }
        return headNode;

    }
}
