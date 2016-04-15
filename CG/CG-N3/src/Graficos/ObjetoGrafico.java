/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import Logica.BoundingBox;
import Logica.Transform;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import javax.media.opengl.GL;

/**
 *
 * @author Avell
 */
public class ObjetoGrafico {
    
    // CONSTRUTORES
    public ObjetoGrafico() {
        cor = Color.BLACK; 
        bound = new BoundingBox();
        pontos = new LinkedList<>();
        pontoConstrucao = null;
        primitiva = GL.GL_LINE_STRIP;
        transform = new Transform();
    }
    
    // DISPLAY
    public void display(GL gl) {
        gl.glColor3f(cor.getRed(), cor.getGreen(), cor.getBlue());
        gl.glPointSize(10);
        gl.glBegin(primitiva);
        
        for (Ponto4D ponto : pontos) {
            gl.glVertex2d(ponto.getX(), ponto.getY());
        }
        
        gl.glEnd();
        
        // Se estiver em construcao, desenhar a linha em construcao
        if (pontoConstrucao != null) {
            Color corConstrucao = Color.GREEN;
            gl.glColor3f(corConstrucao.getRed(), corConstrucao.getGreen(), corConstrucao.getBlue());
            gl.glBegin(GL.GL_LINE_STRIP);
            
            Ponto4D ultimoPonto = getUltimoPonto();
            gl.glVertex2d(ultimoPonto.getX(), ultimoPonto.getY());
            gl.glVertex2d(pontoConstrucao.getX(), pontoConstrucao.getY());
            
            gl.glEnd();
        }
    }
    
    // GET
    public BoundingBox getBound() {
        return bound;
    }

    public Color getCor() {
        return cor;
    }

    public int getPrimitiva() {
        return primitiva;
    }
    
    public Ponto4D getPontoConstrucao() {
        return pontoConstrucao;
    }
    
    // GET - PONTOS
    public List<Ponto4D> getPontos() {
        return pontos;
    }
    
    public Ponto4D getPonto(int index) {
        return pontos.get(index);
    }
    
    public Ponto4D getUltimoPonto() {
        return pontos.get(pontos.size() - 1);
    }
    
    public int getQuantPontos() {
        return pontos.size();
    }
    
    // GET - FILHOS
    public List<ObjetoGrafico> getFilhos() {
        return filhos;
    }
    
    public ObjetoGrafico getFilho(int index) {
        return filhos.get(index);
    }
    
    public int getQuantFilhos() {
        return filhos.size();
    }
    
    // SET
    public void setCor(Color cor) {
        this.cor = cor;
    }

    public void setPrimitiva(int primitiva) {
        this.primitiva = primitiva;
    }
    
    public void setPontoConstrucao(Ponto4D ponto) {
        pontoConstrucao = ponto;
    }
    
    // SET - FILHOS
    public void addFilho(ObjetoGrafico filho) {
        filhos.add(filho);
    }
    
    public ObjetoGrafico removeFilho(int index) {
        return filhos.remove(index);
    }
    
    // SET - PONTOS
    public void addPonto(Ponto4D ponto) {
        pontos.add(ponto);
    }
    
    public Ponto4D removePonto(int index) {
        return pontos.remove(index);
    }
    
    public Ponto4D removeUltimoPonto() {
        return pontos.remove(pontos.size() - 1);
    }
    
    // FUNCOES
    public int indexPonto(Ponto4D clique) {
        for (int i = 0; i < pontos.size(); ++i) {
            if (pontos.get(i).getAreaSelecao().calcula(clique))
                return i;
        }
        
        return -1;
    }
    
    public void encerraObjeto(int index) {
        // Se clicar no primeiro vertice, o poligono sera fechado
        if (index == 0) {
            primitiva = GL.GL_LINE_LOOP;
        } else {
            primitiva = GL.GL_LINE_STRIP;
        }
        
        pontoConstrucao = null;
    }
    
    // ATRIBUTOS - LOGICA
    private BoundingBox bound;
    private Transform transform;
    
    // ATRIBUTOS - GRAFICOS
    private List<Ponto4D> pontos;
    private Ponto4D pontoConstrucao;
    private Color cor;
    private int primitiva;
    
    // ATRIBUTOS - OUTROS
    private List<ObjetoGrafico> filhos;
}