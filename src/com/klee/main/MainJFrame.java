package com.klee.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.klee.about.mAbout;
import com.klee.about.mHelp;
import com.klee.score.ScoreAdd;
import com.klee.score.ScoreManager;
import com.klee.students.StuAdd;
import com.klee.students.StuManager;
import com.klee.user.UserAdd;
import com.klee.welcome.Welcome;

public class MainJFrame extends JFrame {

	private JPanel p;
	JOptionPane jOptionPane1 = new JOptionPane();
	JScrollPane jsp ;
	
	
	public MainJFrame(String title) {
		super(title);
	}

	public void init() {
		final Container c = this.getContentPane();
		p = new HomePanel();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("ѧ����Ϣ����ϵͳ");
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("ѧ������");
		DefaultMutableTreeNode child11 = new DefaultMutableTreeNode("ѧ����Ϣ¼��");
		DefaultMutableTreeNode child12 = new DefaultMutableTreeNode("ѧ����Ϣ����");
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("�ɼ�����");
		DefaultMutableTreeNode child21 = new DefaultMutableTreeNode("ѧ���ɼ�¼��");
		DefaultMutableTreeNode child22 = new DefaultMutableTreeNode("ѧ���ɼ�����");
		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("�û�����");
		DefaultMutableTreeNode child31 = new DefaultMutableTreeNode("����û�");
		DefaultMutableTreeNode child4 = new DefaultMutableTreeNode("����");
		DefaultMutableTreeNode child41 = new DefaultMutableTreeNode("����");
		DefaultMutableTreeNode child42 = new DefaultMutableTreeNode("����");
		root.add(child1);
		root.add(child2);
		root.add(child3);
		root.add(child4);
		child1.add(child11);
		child1.add(child12);
		child2.add(child21);
		child2.add(child22);
		child3.add(child31);
		child4.add(child41);
		child4.add(child42);
		
	
		final JTree tree = new JTree(root);
		
		tree.setPreferredSize(new Dimension(190, 400));
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {

				DefaultMutableTreeNode note = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();

				String name = note.toString();// ��������������

				p.removeAll();
				if (name == "ѧ����Ϣ¼��") {
					StuAdd stuadd = new StuAdd(p);
					stuadd.init();
					p.setLayout(null);
					p.setPreferredSize(new Dimension(300, 500));
					c.add(p, BorderLayout.CENTER);

				} else if (name == "ѧ����Ϣ����") {
		
					StuManager s=new StuManager(p);
					s.init();
					p.setLayout(null);
					p.setPreferredSize(new Dimension(300, 500));
					c.add(p, BorderLayout.CENTER);
	
				}else if(name=="ѧ���ɼ�¼��"){
					ScoreAdd scoreadd=new ScoreAdd(p);
					scoreadd.init();
					
					p.setLayout(null);
					p.setPreferredSize(new Dimension(300, 500));
					c.add(p, BorderLayout.CENTER);
					
				}else if (name == "ѧ���ɼ�����") {
		
					ScoreManager smd=new ScoreManager(p);
					smd.init();
					p.setLayout(null);
					p.setPreferredSize(new Dimension(300, 500));
					c.add(p, BorderLayout.CENTER);
	
				}else if (name == "����û�") {
		
					new UserAdd(p).init();
					p.setLayout(null);
					p.setPreferredSize(new Dimension(300, 500));
					c.add(p, BorderLayout.CENTER);
	
				}else if (name == "����") {
		
					new mAbout(p).init();
					p.setLayout(null);
					p.setPreferredSize(new Dimension(300, 500));
					c.add(p, BorderLayout.CENTER);
	
				}else if (name == "����") {
		
					new mHelp(p).init();
					p.setLayout(null);
					p.setPreferredSize(new Dimension(300, 500));
					c.add(p, BorderLayout.CENTER);
	
				}else if(name=="ѧ����Ϣ����ϵͳ"){
					
					new Welcome(p).init();;
					p.setLayout(null);
					p.setPreferredSize(new Dimension(300, 500));
					c.add(p, BorderLayout.CENTER);
				}

				p.repaint();
			}
		});

		c.add(tree, BorderLayout.WEST);

		this.setLocation(200, 100);
		this.setSize(900, 600);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new CloseWindow(this));
	}

//	public static void main(String[] args) {
//		new MainJFrame("ѧ����Ϣ����ϵͳ").init();
//	}
}