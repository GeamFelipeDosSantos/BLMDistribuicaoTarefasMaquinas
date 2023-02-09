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

        for (int i = 0; i < quantidadeMaquinas; i++) {

            ArrayList<Tarefa> tarefas = new ArrayList<>();

            for (int j = 0; j < quantidadeTarefas; j++) {
                tarefas.add(new Tarefa(j, gerarValorProcessamentoAleatorioTarefa()));
            }

            maquinas.add(new Maquina(i, tarefas));
        }

        gerarMakespanMaquinas(maquinas);
        imprimirMaquinasTarefas(maquinas);
    }

    private void equilibrarMakespan(ArrayList<Maquina> maquinas) {

        for (int i = 0; i < maquinas.size(); i++) {
            if (maquinas.get(i).getMakespan() > maquinas.get(i + 1).getMakespan()) {
                Tarefa tarefaTroca = new Tarefa();
                tarefaTroca = buscarMaiorTarefaMaquina(maquinas.get(i));
                maquinas.get(i + 1).getTarefas().add(tarefaTroca);
                maquinas.get(i) = atualizarTarefasMaquina(maquinas.get(i), tarefaTroca);
                tentar atualizar so a lista de tarefas e nao a maquina inteira(avaliar)
            }
        }

    }

    private int gerarValorProcessamentoAleatorioTarefa() {

        Random aleatorio = new Random();
        int valor = aleatorio.nextInt(100) + 1;
        //System.out.println("Número gerado: " + valor);
        return valor;

    }

    private Tarefa buscarMaiorTarefaMaquina(Maquina maquina) {

        Tarefa maiorTarefa = new Tarefa(0, 0);

        for (int i = 0; i < maquina.getTarefas().size(); i++) {
            if (maquina.getTarefas().get(i).getValorProcessamento() > maiorTarefa.getValorProcessamento()) {
                maiorTarefa = maquina.getTarefas().get(i);
            }
        }

        return maiorTarefa;
    }

    private Maquina atualizarTarefasMaquina(Maquina maquina, Tarefa tarefaRemover) {

        Maquina novaMaquina = Maquina();

        for (Tarefa tarefa : maquina.getTarefas()) {
            if (tarefa == tarefaRemover) {
                maquina.getTarefas().remove(tarefa);
                novaMaquina = maquina;
            }
        }
        return novaMaquina;
    }

    private ArrayList<Maquina> excluirMaquinaDesatualizada(Maquina maquina, ArrayList<Maquina> maquinas) {

        maquinas.remove(maquina);
        return maquinas;
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
                System.out.println("Maquina: " + maquina.getIdMaquina() + " Makespan máquina: " + maquina.getMakespan() + " Tarefa: " + tarefa.getIdTarefa() + " Valor processamento tarefa: " + tarefa.getValorProcessamento());
            }
        }

    }

    private Maquina Maquina() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
