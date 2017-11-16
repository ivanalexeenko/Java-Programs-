package com.tree;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Tree of Numbers:");
        Tree<Integer> tree = new Tree<Integer>(7);

        tree.insert(3);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(14);
        tree.insert(4);
        tree.insert(10);
        tree.insert(12);
        tree.insert(11);
        tree.insert(13);
        tree.insert(8);

        tree.delete(9);
        tree.delete(1);
        tree.delete(3);

        System.out.println("Root->Left->Right Traverse:");
        tree.preOrderTraverse();
        System.out.println();
        System.out.println("Left->Root->Right Traverse:");
        tree.inOrderTraverse();
        System.out.println();
        System.out.println("Left->Right->Root Traverse:");
        tree.postOrderTraverse();
        System.out.println();
        System.out.println();

        System.out.println("Tree of Personal Class:");

        Tree<MusicalRankings> personalTree = new Tree<MusicalRankings>(new MusicalRankings("Nautilus",700));

        personalTree.insert(new MusicalRankings("Foo Fighters",950));
        personalTree.insert(new MusicalRankings("Twin Atlantic",675));
        personalTree.insert(new MusicalRankings("Nirvana",940));
        personalTree.insert(new MusicalRankings("Maroon 5",800));
        personalTree.insert(new MusicalRankings("Eminem",850));
        personalTree.insert(new MusicalRankings("Egor Krid",-10));
        personalTree.insert(new MusicalRankings("SOAD",1000));
        personalTree.insert(new MusicalRankings("SOB",750));


        System.out.println("Root->Left->Right Traverse:");
        personalTree.preOrderTraverse();
        System.out.println();
        System.out.println("Left->Root->Right Traverse:");
        personalTree.inOrderTraverse();
        System.out.println();
        System.out.println("Left->Right->Root Traverse:");
        personalTree.postOrderTraverse();
        System.out.println();
    }
}
