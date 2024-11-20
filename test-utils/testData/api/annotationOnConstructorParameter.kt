/*
 * Copyright 2020 Google LLC
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
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

// TEST PROCESSOR: AnnotationOnConstructorParameterProcessor
// EXPECTED:
// Annotation on property: Anno
// true
// Annotation on constructor param: Anno
// Annotation on constructor param: AnnoWithParamAndFieldTarget
// Annotation on constructor param: JavaAnnoWithParamAndFieldTarget
// true
// END
//FILE: b.java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface JavaAnnoWithParamAndFieldTarget {}

//FILE: a.kt
annotation class Anno

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class AnnoWithParamAndFieldTarget

data class Sample(
    @Anno @AnnoWithParamAndFieldTarget @JavaAnnoWithParamAndFieldTarget val fullName: String
) {
    fun foo() = 0
}
