package Trees;

public class TreeNodeWithDepth  extends TreeNode{
    public int depth;
   
    public TreeNodeWithDepth(int x)
    {
        super(x);
        this.depth = -1;
    }
}