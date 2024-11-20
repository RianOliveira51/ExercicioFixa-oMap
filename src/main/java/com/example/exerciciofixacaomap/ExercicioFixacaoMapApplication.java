package com.example.exerciciofixacaomap;

import com.example.exerciciofixacaomap.entities.Candidate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
/*Na contagem de votos de uma eleição, são gerados vários registros  de votação contendo
* o nome do candidato e a quantidade de votos (formato .csv) que ele obteve em uma urna de votação.
* Você deve fazer um programa para ler os registros de uma votação a partir de um arquivo, e dai gerar
* um relatório consolidando os totais de cada candidato*/

@SpringBootApplication
public class ExercicioFixacaoMapApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        Map<Candidate, Integer> candidateVotes = new HashMap<>();

        System.out.println("Welcome");
        //Localização arquivo
        System.out.println("Enter file full path:");
        String path = sc.nextLine();

        //Começando a ler arquivo
        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();

            //Percorre o arquivo
            while(line != null){
                String[] columns = line.split(";");
                String name = columns[0];
                int vote = Integer.parseInt(columns[1]);
                Candidate candidate = new Candidate(name);

                //Verifica se o mesmo canditado já foi instanciado no map, caso sim, só soma os votos.
                if(candidateVotes.containsKey(candidate)){
                    int sum = candidateVotes.get(candidate) + vote;
                    candidateVotes.put(new Candidate(name), sum);
                }else {
                    candidateVotes.put(new Candidate(name), vote);
                }
                line = br.readLine();
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        //imprime a lista do Map
        for (Candidate key : candidateVotes.keySet()){
            System.out.println(key + ": " + candidateVotes.get(key));
        }
    }

}
