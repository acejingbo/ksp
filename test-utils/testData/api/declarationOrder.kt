/*
 * Copyright 2022 Google LLC
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// WITH_RUNTIME
// TEST PROCESSOR: DeclarationOrderProcessor
// EXPECTED:
// lib.KotlinClass
// b:Ljava/lang/String;
// a:Ljava/lang/String;
// c:Ljava/lang/String;
// isB:Ljava/lang/String;
// isA:Ljava/lang/String;
// isC:Ljava/lang/String;
// noBackingB:Ljava/lang/String;
// noBackingA:Ljava/lang/String;
// noBackingC:Ljava/lang/String;
// noBackingVarB:Ljava/lang/String;
// noBackingVarA:Ljava/lang/String;
// noBackingVarC:Ljava/lang/String;
// privateFun:()V
// protectedFun:()V
// internalFun:()V
// publicFun:()V
// overloaded:(Ljava/lang/String;)Ljava/lang/String;
// overloaded:(I)Ljava/lang/String;
// overloaded:()Ljava/lang/String;
// overloaded:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
// lib.JavaClass
// b:Ljava/lang/String;
// a:Ljava/lang/String;
// c:Ljava/lang/String;
// overloaded:(Ljava/lang/String;)V
// overloaded:(I)V
// overloaded:()V
// overloaded:(Ljava/lang/String;Ljava/lang/String;)V
// KotlinClass
// b:Ljava/lang/String;
// a:Ljava/lang/String;
// c:Ljava/lang/String;
// isB:Ljava/lang/String;
// isA:Ljava/lang/String;
// isC:Ljava/lang/String;
// noBackingB:Ljava/lang/String;
// noBackingA:Ljava/lang/String;
// noBackingC:Ljava/lang/String;
// noBackingVarB:Ljava/lang/String;
// noBackingVarA:Ljava/lang/String;
// noBackingVarC:Ljava/lang/String;
// privateFun:()V
// protectedFun:()V
// internalFun:()V
// publicFun:()V
// overloaded:(Ljava/lang/String;)Ljava/lang/String;
// overloaded:(I)Ljava/lang/String;
// overloaded:()Ljava/lang/String;
// overloaded:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
// JavaClass
// b:Ljava/lang/String;
// a:Ljava/lang/String;
// c:Ljava/lang/String;
// overloaded:(Ljava/lang/String;)V
// overloaded:(I)V
// overloaded:()V
// overloaded:(Ljava/lang/String;Ljava/lang/String;)V
// KotlinCompanion.Companion
// companionObjectProperty:Ljava/lang/String;
// companionObjectPropertyJvmStatic:Ljava/lang/String;
// companionObjectPropertyJvmField:Ljava/lang/String;
// companionObjectPropertyLateinit:Ljava/lang/String;
// companionObjectPropertyConst:Ljava/lang/String;
// companionObjectFunction:(Ljava/lang/String;)V
// companionObjectFunctionJvmStatic:(Ljava/lang/String;)V
// lib.KotlinCompanion.Companion
// companionObjectProperty:Ljava/lang/String;
// companionObjectPropertyJvmStatic:Ljava/lang/String;
// companionObjectPropertyLateinit:Ljava/lang/String;
// companionObjectPropertyConst:Ljava/lang/String;
// companionObjectPropertyJvmField:Ljava/lang/String;
// companionObjectFunction:(Ljava/lang/String;)V
// companionObjectFunctionJvmStatic:(Ljava/lang/String;)V
// equals:(Ljava/lang/Object;)Z
// hashCode:()I
// toString:()Ljava/lang/String;
// <init>:()V
// END
// MODULE: module1
// FILE: lib/KotlinClass.kt
package lib;
class KotlinClass(var property0: String) {
    val b: String = TODO()
    val a: String = TODO()
    val c: String = TODO()

    constructor (param1: Boolean) : this("hello")

    val isB:String = TODO()

    val noBackingB: String
        get() = ""
    val noBackingA: String
        get() = ""
    val noBackingC: String
        get() = ""

    val isA:String = TODO()
    val isC:String = TODO()

    var noBackingVarB: String
        get() = ""
        set(value) {}
    var noBackingVarA: String
        get() = ""
        set(value) {}
    var noBackingVarC: String
        get() = ""
        set(value) {}

    companion object {
        @JvmField var companionPropertyJvmField: String? = null
        @JvmStatic fun companionFunctionJvmStatic(): Unit {}
    }

    private fun privateFun(): Unit = TODO()
    protected fun protectedFun(): Unit = TODO()
    internal fun internalFun(): Unit = TODO()

    constructor () : this("hello")

    fun publicFun(): Unit = TODO()
    fun overloaded(x:String): String = TODO()
    fun overloaded(x:Int): String = TODO()
    fun overloaded(): String = TODO()
    fun overloaded(x:String, y:String): String = TODO()

}
// FILE: lib/JavaClass.java
package lib;
public class JavaClass {
    // notice the non alphabetic order, which is triggering the problem
    String b = "";
    String a = "";
    String c = "";
    void overloaded(String x) {}
    void overloaded(int x) {}
    void overloaded() {}
    void overloaded(String x, String y) {}
}

// FILE: lib/KotlinCompanion.kt
package lib
class KotlinCompanion {
    companion object {
        val companionObjectProperty: String = "hello"
        @JvmStatic
        val companionObjectPropertyJvmStatic: String = "hello"
        @JvmField val companionObjectPropertyJvmField: String = "hello"
        lateinit var companionObjectPropertyLateinit: String
        const val companionObjectPropertyConst: String = "hello"
        fun companionObjectFunction(companionFunctionParam: String) {}
        @JvmStatic
        fun companionObjectFunctionJvmStatic(companionFunctionParam: String) {}
    }
}

// MODULE: main(module1)
// FILE: main.kt
class KotlinClass {
    val b: String? = TODO()
    val a: String = TODO()
    val c: String? = TODO()
    val isB:String = TODO()
    val isA:String = TODO()
    val isC:String = TODO()
    val noBackingB: String
        get() = ""
    val noBackingA: String
        get() = ""
    val noBackingC: String
        get() = ""
    var noBackingVarB: String
        get() = ""
        set(value) {}
    var noBackingVarA: String
        get() = ""
        set(value) {}
    var noBackingVarC: String
        get() = ""
        set(value) {}
    private fun privateFun(): Unit = TODO()
    protected fun protectedFun(): Unit = TODO()
    internal fun internalFun(): Unit = TODO()
    fun publicFun(): Unit = TODO()
    fun overloaded(x:String): String = TODO()
    fun overloaded(x:Int): String = TODO()
    fun overloaded(): String = TODO()
    fun overloaded(x:String, y:String): String = TODO()
}
// FILE: JavaClass.java
public class JavaClass {
    String b = "";
    String a = "";
    String c = "";
    void overloaded(String x) {}
    void overloaded(int x) {}
    void overloaded() {}
    void overloaded(String x, String y) {}
}

// FILE: KotlinCompanion.kt
class KotlinCompanion {
    companion object {
        val companionObjectProperty: String = "hello"
        @JvmStatic
        val companionObjectPropertyJvmStatic: String = "hello"
        @JvmField val companionObjectPropertyJvmField: String = "hello"
        lateinit var companionObjectPropertyLateinit: String
        const val companionObjectPropertyConst: String = "hello"
        fun companionObjectFunction(companionFunctionParam: String) {}
        @JvmStatic
        fun companionObjectFunctionJvmStatic(companionFunctionParam: String) {}
    }
}
