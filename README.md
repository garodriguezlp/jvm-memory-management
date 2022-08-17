# JVM Memory Management

> Examples and practices for the talk on JVM Memory Management basics given to my colleagues at Perficient in August 2022

## Prerequisites

Most of the examples are written with [`jbang`](https://www.jbang.dev/)

There are multiple ways to [install](https://www.jbang.dev/download/) `jbang` on your machine, but here are the options in case
you don't want to install it.

- Powershell:

    ```ps1
    iex "& { $(iwr https://ps.jbang.dev) } [jbang arguments]"
    ```

- GitBash/cygwin/mingwin/WSL:

    ```bash
    curl -ls https://sh.jbang.dev | bash -s - [jbang arguments]
    ```

## Heap and Stack in Action

> The following oneliners assume that you have the `idea` binary in your `$PATH`. If `vscode` is your preferred editor, you can
use `--open=code` instead

- Powershell:

    ```ps1
    iex "& { $(iwr https://ps.jbang.dev) } edit --open=idea StackAndHeap@garodriguezlp/jvm-memory-management"
    ```

- GitBash/cygwin/mingwin/WSL:

    ```bash
    curl -ls https://sh.jbang.dev | bash -s - edit --open=idea StackAndHeap@garodriguezlp/jvm-memory-management
    ```

## Intro into GC

### Running with Java 8

- Powershell:

    ```ps1
    iex "& { $(iwr https://ps.jbang.dev) } run --java 8 --verbose --java-options='-Xmx127m' IntroGarbageCollection@garodriguezlp/jvm-memory-management"
    ```

- GitBash/cygwin/mingwin/WSL:

    ```bash
    curl -ls https://sh.jbang.dev | bash -s - run --java 8 --verbose --java-options="-Xmx127m" IntroGarbageCollection@garodriguezlp/jvm-memory-management
    ```

### Running with Java 11

- Powershell:

    ```ps1
    iex "& { $(iwr https://ps.jbang.dev) } run --java 11 --verbose --java-options='-Xmx127m' IntroGarbageCollection@garodriguezlp/jvm-memory-management"
    ```

- GitBash/cygwin/mingwin/WSL:

    ```bash
    curl -ls https://sh.jbang.dev | bash -s - run --java 11 --verbose --java-options="-Xmx127m" IntroGarbageCollection@garodriguezlp/jvm-memory-management
    ```
