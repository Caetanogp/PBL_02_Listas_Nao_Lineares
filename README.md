# PBL 02 - Árvore Binária de Busca

Repositório com dois trabalhos que utilizam Árvore Binária de Busca (ABB), implementados em Java com interface gráfica em Swing.

## Integrantes

- (preencher)

## Subprojetos

### 1. Ranking de Jogadores (`ranking/`)

Sistema de ranking usando uma ABB não balanceada. Cada jogador tem `nickname` e `ranking` (int, chave da árvore). O programa:

- Lê o arquivo `players.csv` na ordem de leitura e insere os jogadores na árvore.
- Permite inserir, buscar e remover jogadores pela interface gráfica.
- Mostra a árvore com o nickname de cada jogador em cada nó.

Busca e remoção são feitas pelo nickname: a árvore é varrida recursivamente até encontrar o jogador, e a remoção usa o ranking correspondente.

### 2. Código Morse (`morse/`)

ABB do código Morse construída pela heurística: ponto (`.`) vai para a esquerda e traço (`-`) vai para a direita. O programa:

- Preenche a árvore com o alfabeto A-Z e dígitos 0-9.
- Codifica uma palavra digitada em Morse.
- Decodifica uma sequência em Morse (letras separadas por espaço, palavras separadas por `/`).
- Mostra a árvore graficamente.

## Como compilar e rodar

Abrir o terminal na raiz do repositório.

**Ranking de Jogadores:**
```
cd ranking
javac *.java
java Main
```

**Código Morse:**
```
cd morse
javac *.java
java Main
```

## Arquivos

- `players.csv` - dados dos jogadores usados pelo projeto de ranking.
- `ranking/` - código do sistema de ranking.
- `morse/` - código do sistema de Morse.
