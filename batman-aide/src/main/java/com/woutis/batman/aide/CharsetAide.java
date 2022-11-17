/*
 * Copyright 2018-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.woutis.batman.aide;

import java.nio.charset.Charset;

/**
 * TODO-Kweny CharsetAide
 *
 * @author Kweny
 * @since 1.0.0
 */
public class CharsetAide {

    public static Charset toCharset(final Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    public static Charset toCharset(final String charsetName) {
        return charsetName == null ? Charset.defaultCharset() : Charset.forName(charsetName);
    }

    public static String toCharsetName(final String charsetName) {
        return charsetName == null ? Charset.defaultCharset().name() : charsetName;
    }

}
