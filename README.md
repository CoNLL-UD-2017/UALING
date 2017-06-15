**[This code belongs to the "Corpus Selection Approaches for Multilingual Parsing from Raw Text to Universal Dependencies" by Ryan Hornby, Clark Taylor, and Jungyeul Park]**

## Requirements

- java
- ud-treebanks-conll2017 


## Usage

1/POS similarity:

```bash
source batch-pos.sh 
```
it generates conllu.2 files

2/Dependency similarity:

```bash
source batch-dep.sh
```
it generates conllu.3 files

3/Intersection:

```bash
source batch-intersection.sh
```
it generates conllu.4 files


## Train and evaluation:

```bash
source ./train.sh
```

Training with UDPipe1.1

