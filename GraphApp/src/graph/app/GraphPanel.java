package graph.app;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

class GraphPanel extends JPanel {
	
	List<Vertex> vertexes=new LinkedList<Vertex>();
	List<Edge> edges=new LinkedList<Edge>();
	static int counter=0;
	static int n=Graph.n;
	SimpleGraph G=new SimpleGraph(0,new boolean[n][n]);
	
	public GraphPanel() {
		setLayout(null);
	}
	
	@Override
	public void paint(Graphics g) {
		for(Edge c:edges) {
			c.draw(g);
		}
		for(Vertex c:vertexes) {
			c.draw(g);
		}
	}
	
	/**
	 * returns vertex from location (u,v), if it doesnt exists returns null
	 * @param u
	 * @param v
	 * @return
	 */
	public  Vertex getVertex(int u,int v) {
		for(Vertex c:vertexes) {
			if(c.getX()<=u+Main.diam&&c.getX()>=u-Main.diam&&c.getY()<=v+Main.diam&&c.getY()>=v-Main.diam) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * returns edge between vertexes u and v, if it doesnt exists returns null
	 * @param u
	 * @param v
	 * @return
	 */
	public  Edge getEdge(Vertex u,Vertex v) {
		for(Edge e:edges) {
			if(e.getA()==u&&e.getB()==v||e.getA()==v&&e.getB()==u) {
				return e;
			}
		}
		return null;
	}

	/**
	 * adds a vertex to a panel
	 * @param c
	 */
	public void addVertex(Vertex c) {
		if(getVertex(c.getX(),c.getY())==null) {
		this.counter++;
		vertexes.add(c);
		G.V++;
		this.update(this.getGraphics());
		}
	}
	/**
	 * adds a edge to a panel
	 * @param c
	 */
	public void addEdge(Edge c) {
		if(getEdge(c.getA(),c.getB())==null) {
			edges.add(c);
			G.E[c.a.getNumb()][c.b.getNumb()]=G.E[c.b.getNumb()][c.a.getNumb()]=true;
			this.update(this.getGraphics());
		}
	}
	
	/**
	 * remove vertex from panel
	 */
	public void removeVertex(Vertex v) {
		for(Edge e:edges) {
			if(e.getA()==v||e.getB()==v) {
				this.removeEdge(e);
			}
		}
		this.counter--;
		for(Vertex u:vertexes) {
			if(u==v) {
				int a=vertexes.indexOf(u);
				vertexes.remove(a);
				G.remove(v.getNumb());
			}
		}
		this.setOpaque(false);
		this.repaint();
	}
	
	/**
	 * remove edge from panel
	 */
	public void removeEdge(Edge e) {
		System.out.println("removing edge");
		int a;
		for(Edge f:edges) {
			if(f==e) {
				a=edges.indexOf(f);
				edges.remove(a);
			}
		}
		G.E[e.a.getNumb()][e.b.getNumb()]=G.E[e.b.getNumb()][e.a.getNumb()]=false;
		this.setOpaque(false);
		this.repaint();
	}
	
	
	public void clear() {
		SimpleGraph G=new SimpleGraph(0,new boolean[n][n]);
		this.G=G;
		this.vertexes.clear();
		this.edges.clear();
		this.counter=0;
		this.setOpaque(false) ;
		this.repaint();
	}
	
}