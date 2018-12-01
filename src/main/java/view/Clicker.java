package view;

import model.WeightGraph;
import representations.Edge;
import representations.SimpleWeightEdge;
import representations.Vertex;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;


public class Clicker extends MouseAdapter {
    private GraphPanel panel;
    private static int j = 0;
    private static int r = 0;
    private static int p = 0;
    //public static Vertex panel.currentVertex;

    /*
    public Vertex getpanel.currentVertex() {
        return panel.currentVertex;
    }

    public void setpanel.currentVertex(Vertex panel.currentVertex) {
        this.panel.currentVertex = panel.currentVertex;
    }
	*/
    public Clicker(GraphPanel panel) {
        super();
        this.panel = panel;
    }
   
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    	//VERTEX
        if (Main.getCurrently() == Now.VERTEX) {
            panel.addVertex(new Vertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2, panel.counter, null));
        }
        
        
        //EDGE
        if (Main.getCurrently() == Now.EDGE) {
            if (j == 0) {
                panel.setCurrentVertex(panel.getVertex(e.getX() - Main.getDiam()/ 2,
                		e.getY() - Main.getDiam()/ 2));
                if (panel.currentVertex != null) j = 1;
            } 
            else {
                Vertex a = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
                if (a != null) {
                	if(a != panel.currentVertex) {
                		Edge c = new Edge(a, panel.currentVertex, null);
                        panel.addEdge(c);
                        panel.unselect(a);
                        panel.setCurrentVertex(null);
                        j--;
                	}
                	else {
                		j--;
                		panel.unselect(a);
                	}
                }
            }
        }
        
        //SIMPLE WEIGHT EDGE
        if (Main.getCurrently() == Now.SIMPLEWEIGHT) {
            if (j == 0) {
                panel.setCurrentVertex(panel.getVertex(e.getX() - Main.getDiam()/ 2,
                		e.getY() - Main.getDiam()/ 2));
                if (panel.currentVertex != null) j = 1;
            } 
            else {
                Vertex a = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
                if (a != null && a != panel.currentVertex) {
                    if(panel.getSimpleWeightEdge(a, panel.currentVertex) == null) {
                    	String response;
	                    int weight;
	                    boolean leave = false;
	                    while(leave == false) {
	                        try {
	                            response = JOptionPane.showInputDialog("Set weight:");
	                            if (response.equals("")) {
	                                leave = true;
	                                // handling Cancel
	                                //throw new NumberFormatException();
	                            } else {
	                                weight = Integer.parseInt(response);
	                                SimpleWeightEdge we = new SimpleWeightEdge(a, panel.currentVertex, null, weight);
	                                panel.addSimpleWeightEdge(we);
	                                j--;
	                                panel.setCurrentVertex(null);
	                            }
	                            leave = true;
	                        } catch(NumberFormatException ex) {
	                            ex.getLocalizedMessage();
	                        } catch (NullPointerException ex2) {
	                            // handling Cancel
	                            panel.unselect(a);
	                            panel.setCurrentVertex(null);
	                            j = 0;
	                            leave = true;
	                        }
	                    }
                    }
                    else {
                    	panel.unselect(a);
                    	panel.setCurrentVertex(null);
                        j = 0;
                        JOptionPane.showMessageDialog(panel, "This edge already exist!");
                    }
                }
                else {
                	if(a==panel.currentVertex) panel.setCurrentVertex(null);
                	j--;
                }
            }
        }
        if (Main.getCurrently() != Now.EDGE && Main.getCurrently() != Now.SIMPLEWEIGHT) j = 0;

        if (Main.getCurrently() == Now.DFS) {
            Vertex a = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
            if (a != null) {
                panel.G.DFS(panel.G, a.getNumb(), panel);
            }
        }

        if (Main.getCurrently() == Now.DEEPSEARCH) {
            panel.G.DeepSearch(panel.G, panel);
        }
        if (Main.getCurrently() == Now.KRASKAL) {
            WeightGraph.kraskal(panel.G, panel);
        }
        
        if(Main.getCurrently() == Now.PRIM) {
            Vertex start = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
            if(start != null) {
                WeightGraph.prim(panel, start);
            }
        }

	if(Main.getCurrently() == Now.DIJKSTRA) {
            Vertex start = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
            if(start != null) {
                WeightGraph.dijkstra(panel, start);
            }
        }
	    
        if (Main.getCurrently() == Now.BFS) {
            Vertex a = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
            if (a != null) {
                panel.G.BFS(panel.G, a.getNumb(), panel);
            }
        }

        if (Main.getCurrently() == Now.BREADTHSEARCH) {
            panel.G.BreadthSearch(panel.G, panel);
        }

        if (Main.getCurrently() == Now.VERTEXREMOVE) {
            Vertex v = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
            if (v != null) {
                panel.removeVertex(v);
            }
        }

        if (Main.getCurrently() == Now.EDGEREMOVE) {
            if (r == 0) {
                panel.currentVertex = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
                if (panel.currentVertex != null) r = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
                if (a != null) {
                    Edge c = panel.getEdge(a, panel.currentVertex);
                    if (c != null) {
                        panel.removeEdge(c);
                        r--;
                    }
                    SimpleWeightEdge w = panel.getSimpleWeightEdge(a, panel.currentVertex);
                    if (w != null) {
                        panel.removeSimpleWeightEdge(w);
                        r--;
                    }
                }
            }
        }
        if (Main.getCurrently() != Now.EDGEREMOVE) r = 0;

        if (Main.getCurrently() == Now.PATCH) {
            if (p == 0) {
                panel.currentVertex = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
                if (panel.currentVertex != null) p = 1;
            } else {
                Vertex a = panel.getVertex(e.getX() - Main.getDiam()/ 2, e.getY() - Main.getDiam()/ 2);
                if (a != null&&a!=panel.currentVertex) {
                	int P[]=panel.G.shortestPatch(a.getNumb(),panel.currentVertex.getNumb());
                	panel.G.colorPatch(P, panel);
                    p--;
                }
            }
        }
        if (Main.getCurrently() != Now.PATCH) p = 0;
        
        if (Main.getCurrently() == Now.ECCENTRICY) {
        	Vertex a = panel.getVertex(e.getX() - Main.getDiam() / 2, e.getY() - Main.getDiam() / 2);
        	if(a!=null) {
        		int eccentricy = panel.G.eccentricyOfVertex(a.getNumb());
        		JOptionPane.showMessageDialog(panel,"Vertexex eccenticity is equals: "+eccentricy);
        		panel.unselect(a);
        	}
        }
        
        if (Main.getCurrently() == Now.DEGREE) {
        	Vertex a = panel.getVertex(e.getX() - Main.getDiam() / 2, e.getY() - Main.getDiam() / 2);
        	if(a!=null) {
        		int degree = panel.G.degreeOfVertex(a.getNumb());
        		JOptionPane.showMessageDialog(panel,"Vertexes degree is equal: "+degree);
        		panel.unselect(a);
        	}
        }
    }
    
    
}