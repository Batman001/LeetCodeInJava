package search;

/**
 * Created by sunchao on 2018/12/21.
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * <pre>无权有向图和无向图的构建以及实现了图的BFS遍历和DFS遍历:
 * 1>.使用队列Queue实现图的BFS遍历;
 * 2>.递归实现图的DFS遍历;
 * 3>.使用栈Stack迭代实现图的DFS遍历。</pre>
 * @author BATMAN
 */
public class CreateGraph1 {
    int time=0;
    Stack<Vertex1> stackVertex=new Stack<Vertex1>();

    public static void main(String[] args) {
        Graph1 graph=new Graph1();
        CreateGraph1 createGraph=new CreateGraph1();
        createGraph.initialGraph(graph);
        createGraph.outputGraph(graph);
//      System.out.println("DFS搜索路径为(递归实现)：");
//      createGraph.DFS(graph);
        System.out.println("DFS搜索路径为(栈实现):");
        createGraph.stackMain(graph);
//      System.out.println("BFS搜索路径为：");
//      createGraph.BFS(graph);
    }

    /**
     * 根据用户输入的string类型的顶点返回该顶点
     * @param graph 图
     * @param str 输入数据
     * @return返回一个顶点
     */
    public Vertex1 getVertex(Graph1 graph,String str){
        for(int i=0;i<graph.verNum;i++){
            if(graph.vertexArray[i].verName.equals(str)){
                return graph.vertexArray[i];
            }
        }
        return null;
    }

    /**
     * 根据用户输入的数据初始化一个图，以邻接表的形式构建!
     * @param graph 生成的图
     */
    public void initialGraph(Graph1 graph){
        @SuppressWarnings("resource")
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入顶点数和边数：");
        graph.verNum=scan.nextInt();
        graph.edgeNum=scan.nextInt();

        System.out.println("请依次输入定点名称：");
        for(int i=0;i<graph.verNum;i++){
            Vertex1 vertex=new Vertex1();
            String name=scan.next();
            vertex.verName=name;
            vertex.color="white";
            vertex.discoverTime=0;
            vertex.finishTime=0;
            vertex.nextNode=null;
            graph.vertexArray[i]=vertex;
        }

        System.out.println("请依次输入图的便边：");
        for(int i=0;i<graph.edgeNum;i++){
            String preV=scan.next();
            String folV=scan.next();

            Vertex1 v1=getVertex(graph,preV);
            if(v1==null)
                System.out.println("输入边存在图中没有的顶点！");
            Vertex1 v2=new Vertex1();
            v2.verName=folV;
            v2.nextNode=v1.nextNode;
            v1.nextNode=v2;

//          紧接着下面注释的代码加上便是构建无向图的，不加则是构建有向图的！
//          Vertex1 reV2=getVertex(graph,folV);
//          if(reV2==null)
//              System.out.println("输入边存在图中没有的顶点！");
//          Vertex1 reV1=new Vertex1();
//          reV1.verName=preV;
//          reV1.nextNode=reV2.nextNode;
//          reV2.nextNode=reV1;
        }
    }

    /**
     * 输入图的邻接表
     * @param graph 待输出的图
     */
    public void outputGraph(Graph1 graph){
        System.out.println("输出图的邻接链表为：");
        for(int i=0;i<graph.verNum;i++){
            Vertex1 vertex=graph.vertexArray[i];
            System.out.print(vertex.verName);

            Vertex1 current=vertex.nextNode;
            while(current!=null){
                System.out.print("-->"+current.verName);
                current=current.nextNode;
            }
            System.out.println();
        }
    }

    /**
     * DFS遍历辅助函数，标记颜色是辅助，即根据顶点返回其下标
     * @param vertex 顶点
     * @param graph 图
     * @return返回下标
     */
    public int index(Vertex1 vertex,Graph1 graph){
        for(int i=0;i<graph.verNum;i++){
            if(vertex.verName.equals(graph.vertexArray[i].verName))
                return i;
        }
        return -1;
    }

    /**
     * DFS深度优先遍历初始化
     * @param graph 图
     */
    public void DFS(Graph1 graph){
        for(int i=0;i<graph.verNum;i++){
            if(graph.vertexArray[i].color.equals("white")){
                DfsVisit(graph.vertexArray[i],graph);
                System.out.println();
            }
        }
    }

    /**
     * DFS递归函数
     * @param vertex 顶点
     * @param graph 图
     */
    public void DfsVisit(Vertex1 vertex,Graph1 graph){
        vertex.color="gray";
        time=time+1;
        vertex.discoverTime=time;
        System.out.print(vertex.verName+"-->");

        Vertex1 current=vertex.nextNode;
        while(current!=null){
            Vertex1 currentNow=getVertex(graph, current.verName);
            if(currentNow.color.equals("white"))
                DfsVisit(currentNow,graph);
            current=current.nextNode;
        }
        vertex.color="black";
        time=time+1;
        vertex.finishTime=time;
    }

    /**
     * 寻找一个节点的邻接点中是否还有白色节点
     * @param vertex 顶点
     * @param graph 图
     * @return 返回白色节点或是null
     */
    public Vertex1 getAdj(Graph1 graph,Vertex1 vertex){
        Vertex1 ver=getVertex(graph, vertex.verName);
        Vertex1 current=ver.nextNode;
        if(current==null)
            return null;
        else{
            Vertex1 cur=getVertex(graph, current.verName);
            while(current!=null && cur.color.equals("gray")){
                current=current.nextNode;
            }
            if(cur.color.equals("white")){
                Vertex1 currentNow=getVertex(graph, current.verName);
                return currentNow;
            }else{
                return null;
            }
        }

    }

    /**
     * 通过栈实现dfs遍历
     * @param graph 图
     * @param vertex 顶点
     */
    public void stackOperator(Graph1 graph,Vertex1 vertex){
        vertex.color="gray";
        stackVertex.push(vertex);
        System.out.print(vertex.verName+"-->");

        while(!stackVertex.isEmpty()){
            Vertex1 ver=stackVertex.peek();
            Vertex1 current=getAdj(graph,ver);
            if(current!=null){
                stackVertex.push(current);
                current.color="gray";
                System.out.print(current.verName+"-->");
            }else{
                stackVertex.pop();
            }
        }
    }

    /**
     * DFS遍历主函数
     * @param graph
     */
    public void stackMain(Graph1 graph){
        for(int i=0;i<graph.verNum;i++){
            if(graph.vertexArray[i].color.equals("white")){
                stackOperator(graph,graph.vertexArray[i]);
                System.out.println();
            }
        }
    }

    /**
     * BFS广度优先搜索实现
     * @param graph 图
     */
    public void BFS(Graph1 graph){
        Vertex1 current=graph.vertexArray[0];
        current.color="gray";
        time=time+1;
        current.discoverTime=time;

        Queue<Vertex1> queue=new LinkedList<Vertex1>();
        queue.offer(current);
        while(queue.peek()!=null){
            Vertex1 ver=queue.poll();
            time=time+1;
            ver.finishTime=time;
            System.out.print(ver.verName+"-->");

            Vertex1 cur=ver.nextNode;
            while(cur!=null){
                Vertex1 curNow=getVertex(graph, cur.verName);
                if(curNow.color.equals("white")){
                    curNow.color="gray";
                    time=time+1;
                    curNow.discoverTime=time;
                    queue.offer(curNow);
                }
                cur=cur.nextNode;
            }
        }
        System.out.println("null");
    }
}

/*
DFS测试图的边：
v1 v2
v1 v3
v2 v3
v3 v4
v4 v2
v5 v4
v5 v6

BFS测试图的边(10)：
v1 v2
v1 v4
v2 v3
v4 v5
v4 v8
v5 v6
v5 v7
v5 v8
v6 v7
v7 v8
*/
