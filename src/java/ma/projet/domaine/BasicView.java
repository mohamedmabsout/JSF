package ma.projet.domaine;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "treeBasicView")
@ViewScoped
public class BasicView {

    private TreeNode root;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("ISIC", root);
        TreeNode node1 = new DefaultTreeNode("info", root);
        TreeNode node2 = new DefaultTreeNode("RH5", root);

        TreeNode node00 = new DefaultTreeNode("Mabsout", node0);
        //TreeNode node01 = new DefaultTreeNode("Mabsout", node1);
        TreeNode node01 = new DefaultTreeNode("iiii", node1);
      // TreeNode node02 = new DefaultTreeNode("Ziyach", node2);

        node00.getChildren().add(new DefaultTreeNode("mabsout"));
        node00.getChildren().add(new DefaultTreeNode("simo"));
        node01.getChildren().add(new DefaultTreeNode("mabsout"));
        node01.getChildren().add(new DefaultTreeNode("iiiii"));
//       node02.getChildren().add(new DefaultTreeNode("zz"));
       
    }

    public TreeNode getRoot() {
        return root;
    }
}
