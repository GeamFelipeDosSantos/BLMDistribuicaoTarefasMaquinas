package controller;

import java.util.ArrayList;
import java.util.Random;
import model.Maquina;
import model.Tarefa;

/**
 *
 * @author Geam
 */
public class MinimizadorTempo {

    public void minimizarTempoProcessamento() {

        //Cria estrutura das máquinas
        ArrayList<Maquina> maquinas = new ArrayList<>();

        
        int quantidadeMaquinas = 10;
        int quantidadeTarefas = 31;
        
        
        for(int i = 0; i < quantidadeMaquinas; i++){
            
            ArrayList<Tarefa> tarefas = new ArrayList<>();
            
            for (int j = 0; j < quantidadeTarefas; j++) {
                tarefas.add(new Tarefa(j, gerarValorProcessamentoAleatorioTarefa()));
            }
            
            maquinas.add(new Maquina(i, tarefas));          
        }

        gerarMakespanMaquinas(maquinas);
        imprimirMaquinasTarefas(maquinas);
    }
    
    private void equilibrarMakespan(){
        
    }

    
    
    private int gerarValorProcessamentoAleatorioTarefa(){
        
        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(100) + 1;
        //System.out.println("Número gerado: " + valor);
        return valor;
        
    }
    
    private void gerarMakespanMaquinas(ArrayList<Maquina> maquinas) {
        
        for (Maquina maquina : maquinas) {
            float makespanCalculado = 0;
            for (Tarefa tarefa : maquina.getTarefas()) {
                makespanCalculado += tarefa.getValorProcessamento();
            }
            maquina.setMakespan(makespanCalculado);
        }
        
    }
    
    public void imprimirMaquinasTarefas(ArrayList<Maquina> maquinas) {

        for (Maquina maquina : maquinas) {
            for (Tarefa tarefa : maquina.getTarefas()) {
                System.out.println("Maquina: " + maquina.getIdMaquina()+" Makespan máquina: "+maquina.getMakespan() + " Tarefa: " + tarefa.getIdTarefa() + " Valor processamento tarefa: " + tarefa.getValorProcessamento());
            }
        }

    }
    
    

}
