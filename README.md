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

  ```ps1
  jbang edit --open=idea StackAndHeap@garodriguezlp/jvm-memory-management
  ```

## Intro into GC

> If you want to know what's `jbang` doing under the hood, you can use the `--verbose` flag
>
> Other java versions could be chosen by using the `--java` flag. Example: `--java 11`. See [jbang
docs](https://www.jbang.dev/documentation/guide/latest/javaversions.html)

- Running it with Java 8

  ```ps1
  jbang run `
    --java 8 `
    --java-options='-verbose:gc' `
    IntroGarbageCollection@garodriguezlp/jvm-memory-management
  ```

- Running it with Java 8 limiting max heap, then run it with `--java 11` and see the difference after the last GC. The thing here
is that GC in Java 11 is capable of giving back memory to the OS

  ```ps1
  jbang run `
    --java 8 `
    --java-options='-verbose:gc' `
    --java-options='-Xmx127m' `
    IntroGarbageCollection@garodriguezlp/jvm-memory-management
  ```

- Running it with Java 8 limiting max and min heap

  ```ps1
  jbang run `
    --java 8 `
    --java-options='-verbose:gc' `
    --java-options='-Xms127m' `
    --java-options='-Xmx127m' `
    IntroGarbageCollection@garodriguezlp/jvm-memory-management
  ```

- Running `visualvm` could be executed like this if `$JAVA_HOME` and `$VISUALVM_HOME` environment variables are properly set
(install the [Visual GC plugin](https://visualvm.github.io/plugins.html) first)

  ```ps1
  & $env:VISUALVM_HOME\bin\visualvm.exe --jdkhome $env:JAVA_HOME --console suppress
  ```

## Memory Leak

- Run the following `jbang` powered class

  ```ps1
  jbang run `
    --java 11 `
    --java-options='-verbose:gc' `
    --java-options='-Xmx256m' `
    --java-options='-XX:+HeapDumpOnOutOfMemoryError' `
    MemoryLeak@garodriguezlp/jvm-memory-management
  ```

- Open `visualvm` and connect to the process (don't forget to install the [Visual GC
plugin](https://visualvm.github.io/plugins.html))

  ```ps1
  & $env:VISUALVM_HOME\bin\visualvm.exe --jdkhome $env:JAVA_HOME --console suppress
  ```

- Open [Eclipse Memory Analyzer tool](https://www.eclipse.org/mat/) and load the heap dump file

  ```ps1
  & $env:MAT_HOME\eclipsec.exe
  ```
