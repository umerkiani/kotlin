package crossinlineLiteral

fun main(args: Array<String>) {
    callFromTopLevel()
    A().callFromClass()
    A.callFromCompanion()
}

fun callFromTopLevel() {
    simpleCall {
        //Breakpoint!
        val a = 1
    }

    lambdaCall {
        //Breakpoint!
        val a = 1
    }

    inlinedLambdaCall {
        //Breakpoint!
        val a = 1
    }

    objectCall {
        //Breakpoint!
        val a = 1
    }

    multipleCrossInline({
        //Breakpoint!
        val a = 1
    }, {
        //Breakpoint!
        val a = 1
    })

    lambdaInInlinedLambdaCall {
        //Breakpoint!
        val a = 1
    }
}

class A {
    fun callFromClass() {
        objectCall {
            //Breakpoint!
            val a = 1
        }
    }

    companion object {
        fun callFromCompanion() {
            objectCall {
                //Breakpoint!
                val a = 1
            }
        }
    }
}

inline fun simpleCall(crossinline f: (Int) -> Unit) {
    //Breakpoint!
    f(1)
}

inline fun lambdaCall(crossinline f: (Int) -> Unit) {
    val a = {
        //Breakpoint!
        f(1)
    }

    a.invoke()
}

inline fun inlinedLambdaCall(crossinline f: (Int) -> Unit) {
    run {
        //Breakpoint!
        f(1)
    }
}

inline fun objectCall(crossinline f: (Int) -> Unit) {
    val a = object {
        fun objFun() {
            //Breakpoint!
            f(1)
        }
    }

    a.objFun()
}

inline fun multipleCrossInline(crossinline f1: (Int) -> Unit, crossinline f2: (Int) -> Unit) {
    val a1 = {
        //Breakpoint!
        f1(1)
    }

    a1.invoke()

    val a2 = {
        //Breakpoint!
        f2(1)
    }

    a2.invoke()
}

inline fun lambdaInInlinedLambdaCall(crossinline f: (Int) -> Unit) {
    run {
        {
            //Breakpoint!
            f(1)
        }()

    }
}

// RESUME: 17