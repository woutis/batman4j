/*
 * Copyright 2018-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.woutis.batman.aide;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * null-safe 的 String 操作。
 *
 * <p>Modeled on:
 * <ul>
 *     <li><a href="https://github.com/apache/commons-lang">Apache Commons Lang</a></li>
 *     <li><a href="https://github.com/google/guava">Google Guava</a></li>
 * </ul>
 *
 * @author Kweny
 * @since 1.0.0
 */
public class StringAide {

    /**
     * 通常情况 {@link StringAide} 不应被实例化，而是应该直接使用其静态方法，如 {@code StringAide.trim(" foo ")}。
     *
     * <p>将公共方法设为 public 以便需要 JavaBean 才能运行的工具使用。
     */
    public StringAide() {
    }

    /**
     * 一个空格字符
     */
    public static final String SPACE = " ";

    /**
     * 一个空字符串 {@code ""}
     */
    public static final String EMPTY = "";

    /**
     * 一个换行符 LF {@code "\n"}
     *
     * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">JLF: Escape Sequences for Character and String Literals</a>
     */
    public static final String LF = "\n";

    /**
     * 一个回车符 CR {@code "\r"}
     *
     * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">JLF: Escape Sequences for Character and String Literals</a>
     */
    public static final String CR = "\r";

    /**
     * 表示搜索失败时的索引值
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * <p>获取一个字符序列 {@link CharSequence} 的长度，如果字符序列为 {@code null} 则返回 {@code 0}。</p>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 指定字符序列的长度，如果字符序列为 {@code null} 则返回 {@code 0}
     */
    public static int length(final CharSequence input) {
        return input == null ? 0 : input.length();
    }

    /**
     * 以 null-safe 方式调用 {@link String#getBytes(Charset)}
     *
     * @param input 字符串
     * @param charset 字符集，如果为空则使用默认字符集
     * @return 如果字符串为 null，则为空 byte[]，否则为 {@link String#getBytes(Charset)} 的结果
     * @see String#getBytes(Charset)
     */
    public static byte[] getBytes(final String input, final Charset charset) {
        return input == null ? ArrayAide.EMPTY_BYTE_ARRAY : input.getBytes(CharsetAide.toCharset(charset));
    }

    /**
     * 以 null-safe 方式调用 {@link String#getBytes(String)}
     *
     * @param input 字符串
     * @param charsetName 字符集名称，如果为空则使用默认字符集
     * @return 如果字符串为 null，则为空 byte[]，否则为 {@link String#getBytes(String)} 的结果
     * @see String#getBytes(String)
     */
    public static byte[] getBytes(final String input, final String charsetName) throws UnsupportedEncodingException {
        return input == null ? ArrayAide.EMPTY_BYTE_ARRAY : input.getBytes(CharsetAide.toCharsetName(charsetName));
    }

    /**
     * 以 null-safe 方式调用 {@link String#getBytes()}
     *
     * @param input 字符串
     * @return 如果字符串为 null，则为空 byte[]，否则为 {@link String#getBytes()} 的结果
     * @see String#getBytes()
     */
    public static byte[] getBytes(final String input) {
        return input == null ? ArrayAide.EMPTY_BYTE_ARRAY : input.getBytes();
    }

    /**
     * <p>检查一个字符序列 {@link CharSequence} 是否为 空 或 {@code null}。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <pre>
     * StringAide.isEmpty(null)      = true
     * StringAide.isEmpty("")        = true
     * StringAide.isEmpty(" ")       = false
     * StringAide.isEmpty("bob")     = false
     * StringAide.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当指定的字符序列为 空 或者 {@code null} 时返回 {@code true}
     */
    public static boolean isEmpty(final CharSequence input) {
        return input == null || input.length() == 0;
    }

    /**
     * <p>检查一个字符序列 {@link CharSequence} 是否不为 空 且不为 {@code null}。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <pre>
     * StringAide.isNotEmpty(null)      = false
     * StringAide.isNotEmpty("")        = false
     * StringAide.isNotEmpty(" ")       = true
     * StringAide.isNotEmpty("bob")     = true
     * StringAide.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当指定的字符序列不为 空 并且不为 {@code null} 时返回 {@code true}
     */
    public static boolean isNotEmpty(final CharSequence input) {
        return !isEmpty(input);
    }

    /**
     * <p>检查多个字符序列是否全都为 空 或者 {@code null}。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <pre>
     * StringAide.isAllEmpty((String) null)             = true
     * StringAide.isAllEmpty((String[]) null)             = true
     * StringAide.isAllEmpty(null, "")         = true
     * StringAide.isAllEmpty("", "")         = true
     * StringAide.isAllEmpty(new String[] {})  = true
     * StringAide.isAllEmpty(null, "foo")      = false
     * StringAide.isAllEmpty("", "bar")        = false
     * StringAide.isAllEmpty("bob", "")        = false
     * StringAide.isAllEmpty("  bob  ", null)  = false
     * StringAide.isAllEmpty(" ", "bar")       = false
     * StringAide.isAllEmpty("foo", "bar")     = false
     * </pre>
     *
     * @param inputs 要检查的字符序列数组，可以为 {@code null} 或 空数组
     * @return 当字符序列数组中的所有元素都为 空 或者 {@code null} 时返回 {@code true}
     */
    public static boolean isAllEmpty(final CharSequence... inputs) {
        if (ArrayAide.isEmpty(inputs)) {
            return true;
        }
        for (CharSequence input : inputs) {
            if (isNotEmpty(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查多个字符序列中是否存在至少一个 空 或者 {@code null}。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <pre>
     * StringAide.isAnyEmpty((String) null)    = true
     * StringAide.isAnyEmpty((String[]) null)  = false
     * StringAide.isAnyEmpty(null, "foo")      = true
     * StringAide.isAnyEmpty("", "bar")        = true
     * StringAide.isAnyEmpty("bob", "")        = true
     * StringAide.isAnyEmpty("  bob  ", null)  = true
     * StringAide.isAnyEmpty(" ", "bar")       = false
     * StringAide.isAnyEmpty("foo", "bar")     = false
     * StringAide.isAnyEmpty(new String[]{})   = false
     * StringAide.isAnyEmpty(new String[]{""}) = true
     * </pre>
     *
     * @param inputs 要检查的字符序列数组，可以为 {@code null} 或空数组
     * @return 当字符序列数组中存在至少一个 空 或 {@code null} 元素时返回 {@code true}
     */
    public static boolean isAnyEmpty(final CharSequence... inputs) {
        if (ArrayAide.isEmpty(inputs)) {
            return false;
        }
        for (CharSequence input : inputs) {
            if (isEmpty(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>检查多个字符序列是否全都不为 空 且不为 {@code null}。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <pre>
     * StringAide.isNoneEmpty((String) null)    = false
     * StringAide.isNoneEmpty((String[]) null)  = true
     * StringAide.isNoneEmpty(null, "foo")      = false
     * StringAide.isNoneEmpty("", "bar")        = false
     * StringAide.isNoneEmpty("bob", "")        = false
     * StringAide.isNoneEmpty("  bob  ", null)  = false
     * StringAide.isNoneEmpty(new String[] {})  = true
     * StringAide.isNoneEmpty(new String[]{""}) = false
     * StringAide.isNoneEmpty(" ", "bar")       = true
     * StringAide.isNoneEmpty("foo", "bar")     = true
     * </pre>
     *
     * @param inputs 要检查的字符序列数组，可以为 {@code null} 或空数组
     * @return 当字符序列数组中的所有元素全都不为 空 并且不为 {@code null} 时返回 {@code true}
     */
    public static boolean isNoneEmpty(final CharSequence... inputs) {
        return !isAnyEmpty(inputs);
    }

    /**
     * <p>检查一个字符序列 {@link CharSequence} 是否为 空 或 {@code null} 或 空白。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <p>空白：指由空格、制表符、换行符、回车符等组成的空白字符序列，
     * 完整空白字符列表详见 {@link Character#isWhitespace(char)} 的定义。</p>
     *
     * <pre>
     * StringAide.isBlank(null)      = true
     * StringAide.isBlank("")        = true
     * StringAide.isBlank(" ")       = true
     * StringAide.isBlank("bob")     = false
     * StringAide.isBlank("  bob  ") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当指定的字符序列为 空 或 {@code null} 或 空白 时返回 {@code true}
     */
    public static boolean isBlank(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查一个字符序列是否不为 空 且不为 {@code null} 且不为 空白。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <p>空白：指由空格、制表符、换行符、回车符等组成的空白字符序列，
     * 完整空白字符列表详见 {@link Character#isWhitespace(char)} 的定义。</p>
     *
     * <pre>
     * StringAide.isNotBlank(null)      = false
     * StringAide.isNotBlank("")        = false
     * StringAide.isNotBlank(" ")       = false
     * StringAide.isNotBlank("bob")     = true
     * StringAide.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当指定的字符序列不为 空 且不为 {@code null} 且不为 空白 时返回 {@code true}
     */
    public static boolean isNotBlank(final CharSequence input) {
        return !isBlank(input);
    }

    /**
     * <p>检查多个字符序列是否全都为 空 或 {@code null} 或 空白。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <p>空白：指由空格、制表符、换行符、回车符等组成的空白字符序列，
     * 完整空白字符列表详见 {@link Character#isWhitespace(char)} 的定义。</p>
     *
     * <pre>
     * StringAide.isAllBlank(null)             = true
     * StringAide.isAllBlank(null, "foo")      = false
     * StringAide.isAllBlank(null, null)       = true
     * StringAide.isAllBlank("", "bar")        = false
     * StringAide.isAllBlank("bob", "")        = false
     * StringAide.isAllBlank("  bob  ", null)  = false
     * StringAide.isAllBlank(" ", "bar")       = false
     * StringAide.isAllBlank("foo", "bar")     = false
     * StringAide.isAllBlank(new String[] {})  = true
     * StringAide.isAllBlank("", "", " ")  = true
     * </pre>
     *
     * @param inputs 要检查的字符序列数组，可以为 {@code null} 或 空数组
     * @return 当字符序列数组中的所有元素都为 空 或 {@code null} 或 空白 时返回 {@code true}
     */
    public static boolean isAllBlank(final CharSequence... inputs) {
        if (ArrayAide.isEmpty(inputs)) {
            return true;
        }
        for (CharSequence input : inputs) {
            if (isNotBlank(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查多个字符序列中是否存在至少一个 空 或 {@code null} 或 空白。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <p>空白：指由空格、制表符、换行符、回车符等组成的空白字符序列，
     * 完整空白字符列表详见 {@link Character#isWhitespace(char)} 的定义。</p>
     *
     * <pre>
     * StringAide.isAnyBlank((String) null)    = true
     * StringAide.isAnyBlank((String[]) null)  = false
     * StringAide.isAnyBlank(null, "foo")      = true
     * StringAide.isAnyBlank(null, null)       = true
     * StringAide.isAnyBlank("", "bar")        = true
     * StringAide.isAnyBlank("bob", "")        = true
     * StringAide.isAnyBlank("  bob  ", null)  = true
     * StringAide.isAnyBlank(" ", "bar")       = true
     * StringAide.isAnyBlank(new String[] {})  = false
     * StringAide.isAnyBlank(new String[]{""}) = true
     * StringAide.isAnyBlank("foo", "bar")     = false
     * </pre>
     *
     * @param inputs 要检查的字符序列数组，可以为 {@code null} 或空数组
     * @return 当字符序列数组中存在至少一个 空 或 {@code null} 或 空白 元素时返回 {@code true}
     */
    public static boolean isAnyBlank(final CharSequence... inputs) {
        if (ArrayAide.isEmpty(inputs)) {
            return false;
        }
        for (CharSequence input : inputs) {
            if (isBlank(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>检查多个字符序列是否全都不为 空 且不为 {@code null} 且不为 空白。</p>
     *
     * <p>空：指空字符序列（{@code ""}）。</p>
     *
     * <p>空白：指由空格、制表符、换行符、回车符等组成的空白字符序列，
     * 完整空白字符列表详见 {@link Character#isWhitespace(char)} 的定义。</p>
     *
     * <pre>
     * StringAide.isNoneBlank((String) null)    = false
     * StringAide.isNoneBlank((String[]) null)  = true
     * StringAide.isNoneBlank(null, "foo")      = false
     * StringAide.isNoneBlank(null, null)       = false
     * StringAide.isNoneBlank("", "bar")        = false
     * StringAide.isNoneBlank("bob", "")        = false
     * StringAide.isNoneBlank("  bob  ", null)  = false
     * StringAide.isNoneBlank(" ", "bar")       = false
     * StringAide.isNoneBlank(new String[] {})  = true
     * StringAide.isNoneBlank(new String[]{""}) = false
     * StringAide.isNoneBlank("foo", "bar")     = true
     * </pre>
     *
     * @param inputs 要检查的字符序列数组，可以为 {@code null} 或空数组
     * @return 当字符序列数组中的所有元素全都不为 空 且不为 {@code null} 且不为 空白 时返回 {@code true}
     */
    public static boolean isNoneBlank(final CharSequence... inputs) {
        return !isAnyBlank(inputs);
    }

    /**
     * <p>检查字符序列是否仅包含小写字符。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code false}。</p>
     *
     * <pre>
     * StringAide.isAllLowerCase(null)   = false
     * StringAide.isAllLowerCase("")     = false
     * StringAide.isAllLowerCase("  ")   = false
     * StringAide.isAllLowerCase("abc")  = true
     * StringAide.isAllLowerCase("abC")  = false
     * StringAide.isAllLowerCase("ab c") = false
     * StringAide.isAllLowerCase("ab1c") = false
     * StringAide.isAllLowerCase("ab/c") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列 {@link #isNotEmpty} 且仅包含小写字符时返回 {@code true}
     */
    public static boolean isAllLowerCase(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isLowerCase(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列是否包含至少一个小写字符。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code false}。</p>
     *
     * <pre>
     * StringAide.isAnyLowerCase(null)   = false
     * StringAide.isAnyLowerCase("")     = false
     * StringAide.isAnyLowerCase("  ")   = false
     * StringAide.isAnyLowerCase("ABC")   = false
     * StringAide.isAnyLowerCase("123")   = false
     * StringAide.isAnyLowerCase("abc")  = true
     * StringAide.isAnyLowerCase("abC")  = true
     * StringAide.isAnyLowerCase("ab c") = true
     * StringAide.isAnyLowerCase("ab1c") = true
     * StringAide.isAnyLowerCase("ab/c") = true
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列 {@link #isNotEmpty} 且包含至少一个小写字符时返回 {@code true}
     */
    public static boolean isAnyLowerCase(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (Character.isLowerCase(input.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>检查字符序列是否不包含小写字符。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isNoneLowerCase(null)   = true
     * StringAide.isNoneLowerCase("")     = true
     * StringAide.isNoneLowerCase("  ")   = true
     * StringAide.isNoneLowerCase("ABC")  = true
     * StringAide.isNoneLowerCase("123")  = true
     * StringAide.isNoneLowerCase("abc")  = false
     * StringAide.isNoneLowerCase("abC")  = false
     * StringAide.isNoneLowerCase("ab c") = false
     * StringAide.isNoneLowerCase("ab1c") = false
     * StringAide.isNoneLowerCase("ab/c") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列 {@link #isEmpty} 或者不包含任何小写字符时返回 {@code true}
     */
    public static boolean isNoneLowerCase(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (Character.isLowerCase(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列是否仅包含大写字符。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code false}。</p>
     *
     * <pre>
     * StringAide.isAllUpperCase(null)   = false
     * StringAide.isAllUpperCase("")     = false
     * StringAide.isAllUpperCase("  ")   = false
     * StringAide.isAllUpperCase("ABC")  = true
     * StringAide.isAllUpperCase("aBC")  = false
     * StringAide.isAllUpperCase("A C")  = false
     * StringAide.isAllUpperCase("A1C")  = false
     * StringAide.isAllUpperCase("A/C")  = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列 {@link #isNotEmpty} 且仅包含大写字符时返回 {@code true}
     */
    public static boolean isAllUpperCase(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isUpperCase(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列是否包含至少一个大写字符。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code false}。</p>
     *
     * <pre>
     * StringAide.isAnyUpperCase(null)   = false
     * StringAide.isAnyUpperCase("")     = false
     * StringAide.isAnyUpperCase("  ")   = false
     * StringAide.isAnyUpperCase("abc")   = false
     * StringAide.isAnyUpperCase("123")   = false
     * StringAide.isAnyUpperCase("ABC")  = true
     * StringAide.isAnyUpperCase("aBC")  = true
     * StringAide.isAnyUpperCase("A C")  = true
     * StringAide.isAnyUpperCase("A1C")  = true
     * StringAide.isAnyUpperCase("A/C")  = true
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列 {@link #isNotEmpty} 且包含至少一个大写字符时返回 {@code true}
     */
    public static boolean isAnyUpperCase(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (Character.isUpperCase(input.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>检查字符序列是否不包含大写字符。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isNoneUpperCase(null)   = true
     * StringAide.isNoneUpperCase("")     = true
     * StringAide.isNoneUpperCase("  ")   = true
     * StringAide.isNoneUpperCase("abc")   = true
     * StringAide.isNoneUpperCase("123")   = true
     * StringAide.isNoneUpperCase("ABC")  = false
     * StringAide.isNoneUpperCase("aBC")  = false
     * StringAide.isNoneUpperCase("A C")  = false
     * StringAide.isNoneUpperCase("A1C")  = false
     * StringAide.isNoneUpperCase("A/C")  = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列 {@link #isEmpty} 或者不包含任何大写字符时返回 {@code true}
     */
    public static boolean isNoneUpperCase(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (Character.isUpperCase(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列的字符是否为大小写混合。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code false}。</p>
     *
     * <pre>
     * StringAide.isMixedCase(null)    = false
     * StringAide.isMixedCase("")      = false
     * StringAide.isMixedCase("ABC")   = false
     * StringAide.isMixedCase("abc")   = false
     * StringAide.isMixedCase("aBc")   = true
     * StringAide.isMixedCase("A c")   = true
     * StringAide.isMixedCase("A1c")   = true
     * StringAide.isMixedCase("a/C")   = true
     * StringAide.isMixedCase("aC\t")  = true
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当指定字符序列 {@link #isNotEmpty} 并且其中的字符为大小写混合时返回 {@code true}
     */
    public static boolean isMixedCase(final CharSequence input) {
        final int length = length(input);
        if (length == 0 || length == 1) {
            return false;
        }
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;
        for (int i = 0; i < length; i++) {
            if (containsUpperCase && containsLowerCase) {
                return true;
            } else if (Character.isUpperCase(input.charAt(i))) {
                containsUpperCase = true;
            } else if (Character.isLowerCase(input.charAt(i))) {
                containsLowerCase = true;
            }
        }
        return containsUpperCase && containsLowerCase;
    }

    /**
     * <p>检查字符序列中是否仅包含 Unicode 字母。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code false}。</p>
     *
     * <pre>
     * StringAide.isAlpha(null)   = false
     * StringAide.isAlpha("")     = false
     * StringAide.isAlpha("  ")   = false
     * StringAide.isAlpha("abc")  = true
     * StringAide.isAlpha("ab2c") = false
     * StringAide.isAlpha("ab-c") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列 {@link #isNotEmpty} 且仅包含 Unicode 字母时返回 {@code true}
     * @see Character#isLetter(char)
     */
    public static boolean isAlpha(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列中是否仅包含 Unicode 字母和数字。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code false}。</p>
     *
     * <pre>
     * StringAide.isAlphanumeric(null)   = false
     * StringAide.isAlphanumeric("")     = false
     * StringAide.isAlphanumeric("  ")   = false
     * StringAide.isAlphanumeric("abc")  = true
     * StringAide.isAlphanumeric("ab c") = false
     * StringAide.isAlphanumeric("ab2c") = true
     * StringAide.isAlphanumeric("ab-c") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列 {@link #isNotEmpty} 且仅包含 Unicode 字母和数字时返回 {@code true}
     * @see Character#isLetterOrDigit(char)
     */
    public static boolean isAlphanumeric(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列中是否仅包含 Unicode 字母和空格（{@code ' '}）。</p>
     *
     * <p>若指定字符序列为 {@code null} 将返回 {@code false}，
     * 若指定字符序列为 空（{@code ""}）将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isAlphaSpace(null)   = false
     * StringAide.isAlphaSpace("")     = true
     * StringAide.isAlphaSpace("  ")   = true
     * StringAide.isAlphaSpace("a\nc")  = false
     * StringAide.isAlphaSpace("abc")  = true
     * StringAide.isAlphaSpace("ab c") = true
     * StringAide.isAlphaSpace("ab2c") = false
     * StringAide.isAlphaSpace("ab-c") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列不为 {@code null} 且仅包含 Unicode 字母和空格时返回 {@code true}
     * @see Character#isLetter(char)
     */
    public static boolean isAlphaSpace(final CharSequence input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            final char nowChar = input.charAt(i);
            if (nowChar != ' ' && !Character.isLetter(nowChar)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列中是否仅包含 Unicode 字母和空白。</p>
     *
     * <p>空白：指由空格、制表符、换行符、回车符等组成的空白字符序列，
     * 完整空白字符列表详见 {@link Character#isWhitespace(char)} 的定义。</p>
     *
     * <p>若指定字符序列为 {@code null} 将返回 {@code false}，
     * 若指定字符序列为 空（{@code ""}）将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isAlphaWhitespace(null)   = false
     * StringAide.isAlphaWhitespace("")     = true
     * StringAide.isAlphaWhitespace("  ")   = true
     * StringAide.isAlphaWhitespace("a\nb")   = true
     * StringAide.isAlphaWhitespace("abc")  = true
     * StringAide.isAlphaWhitespace("ab c") = true
     * StringAide.isAlphaWhitespace("ab2c") = false
     * StringAide.isAlphaWhitespace("ab-c") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列不为 {@code null} 且仅包含 Unicode 字母和空白时返回 {@code true}
     * @see Character#isLetter(char)
     */
    public static boolean isAlphaWhitespace(final CharSequence input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            final char nowChar = input.charAt(i);
            if (!Character.isWhitespace(nowChar) && !Character.isLetter(nowChar)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列中是否仅包含 Unicode 字母、数字、空格（{@code ' '}）。</p>
     *
     * <p>若指定字符序列为 {@code null} 将返回 {@code false}，
     * 若指定字符序列为 空（{@code ""}）将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isAlphanumericSpace(null)   = false
     * StringAide.isAlphanumericSpace("")     = true
     * StringAide.isAlphanumericSpace("  ")   = true
     * StringAide.isAlphanumericSpace("abc")  = true
     * StringAide.isAlphanumericSpace("ab c") = true
     * StringAide.isAlphanumericSpace("ab2c") = true
     * StringAide.isAlphanumericSpace("ab-c") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列不为 {@code null} 且仅包含 Unicode 字母、数字、空格（{@code ' '}）时返回 {@code true}
     * @see Character#isLetterOrDigit(char)
     */
    public static boolean isAlphanumericSpace(final CharSequence input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            final char nowChar = input.charAt(i);
            if (nowChar != ' ' && !Character.isLetterOrDigit(nowChar) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列中是否仅包含 Unicode 数字。
     * 小数点和正负号不是 Unicode 数字，会返回 {@code false}。</p>
     *
     * <p>若指定字符序列为 {@code null} 或 空（{@code ""}）时将返回 {@code false}。</p>
     *
     * <p>注意，即使指定的字符序列通过了该方法的检查，
     * 如果值超出 int 或 long 范围，
     * 在由 Integer.parseInt 和 Long.parseLong 进行转换时仍有可能产生 NumberFormatException。</p>
     *
     * <pre>
     * StringAide.isNumeric(null)   = false
     * StringAide.isNumeric("")     = false
     * StringAide.isNumeric("  ")   = false
     * StringAide.isNumeric("123")  = true
     * StringAide.isNumeric("\u0967\u0968\u0969")  = true
     * StringAide.isNumeric("12 3") = false
     * StringAide.isNumeric("ab2c") = false
     * StringAide.isNumeric("12-3") = false
     * StringAide.isNumeric("12.3") = false
     * StringAide.isNumeric("-123") = false
     * StringAide.isNumeric("+123") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列不为 {@code null} 且仅包含 Unicode 数字时返回 {@code true}
     * @see Character#isDigit(char)
     */
    public static boolean isNumeric(final CharSequence input) {
        final int length = length(input);
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列中是否仅包含 Unicode 数字和空格（{@code ' '}）。
     * 小数点和正负号不是 Unicode 数字，会返回 {@code false}。</p>
     *
     * <p>若指定字符序列为 {@code null} 将返回 {@code false}，
     * 若指定字符序列为 空（{@code ""}）将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isNumericSpace(null)   = false
     * StringAide.isNumericSpace("")     = true
     * StringAide.isNumericSpace("  ")   = true
     * StringAide.isNumericSpace("123")  = true
     * StringAide.isNumericSpace("12 3") = true
     * StringAide.isNumericSpace("\u0967\u0968\u0969")  = true
     * StringAide.isNumericSpace("\u0967\u0968 \u0969")  = true
     * StringAide.isNumericSpace("ab2c") = false
     * StringAide.isNumericSpace("12-3") = false
     * StringAide.isNumericSpace("12.3") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列不为 {@code null} 且仅包含 Unicode 数字和空格时返回 {@code true}
     * @see Character#isDigit(char)
     */
    public static boolean isNumericSpace(final CharSequence input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            final char nowChar = input.charAt(i);
            if (nowChar != ' ' && !Character.isDigit(nowChar)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列中是否仅包含 Unicode 数字和空白。
     * 小数点和正负号不是 Unicode 数字，会返回 {@code false}。</p>
     *
     * <p>空白：指由空格、制表符、换行符、回车符等组成的空白字符序列，
     * 完整空白字符列表详见 {@link Character#isWhitespace(char)} 的定义。</p>
     *
     * <p>若指定字符序列为 {@code null} 将返回 {@code false}，
     * 若指定字符序列为 空（{@code ""}）将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isNumericWhitespace(null)   = false
     * StringAide.isNumericWhitespace("")     = true
     * StringAide.isNumericWhitespace("  ")   = true
     * StringAide.isNumericWhitespace("123")  = true
     * StringAide.isNumericWhitespace("12\n3")  = true
     * StringAide.isNumericWhitespace("12 3") = true
     * StringAide.isNumericWhitespace("\u0967\u0968\u0969")  = true
     * StringAide.isNumericWhitespace("\u0967\u0968 \u0969")  = true
     * StringAide.isNumericWhitespace("ab2c") = false
     * StringAide.isNumericWhitespace("12-3") = false
     * StringAide.isNumericWhitespace("12.3") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列不为 {@code null} 且仅包含 Unicode 数字和空白时返回 {@code true}
     */
    public static boolean isNumericWhitespace(final CharSequence input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            final char nowChar = input.charAt(i);
            if (!Character.isWhitespace(nowChar) && !Character.isDigit(nowChar)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列是否仅包含空白字符。</p>
     *
     * <p>空白：指由空格、制表符、换行符、回车符等组成的空白字符序列，
     * 完整空白字符列表详见 {@link Character#isWhitespace(char)} 的定义。</p>
     *
     * <p>若指定字符序列为 {@code null} 将返回 {@code false}，
     * 若指定字符序列为 空（{@code ""}）将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isWhitespace(null)   = false
     * StringAide.isWhitespace("")     = true
     * StringAide.isWhitespace("  ")   = true
     * StringAide.isWhitespace("abc")  = false
     * StringAide.isWhitespace("ab2c") = false
     * StringAide.isWhitespace("ab-c") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列不为 {@code null} 且仅包含空白字符时返回 {@code true}
     */
    public static boolean isWhitespace(final CharSequence input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查字符序列是否仅包含 ASCII 可打印字符。</p>
     *
     * <p>若指定字符序列为 {@code null} 将返回 {@code false}，
     * 若指定字符序列为 空（{@code ""}）将返回 {@code true}。</p>
     *
     * <pre>
     * StringAide.isAsciiPrintable(null)     = false
     * StringAide.isAsciiPrintable("")       = true
     * StringAide.isAsciiPrintable(" ")      = true
     * StringAide.isAsciiPrintable("Ceki")   = true
     * StringAide.isAsciiPrintable("ab2c")   = true
     * StringAide.isAsciiPrintable("!ab-c~") = true
     * StringAide.isAsciiPrintable("\u0020") = true
     * StringAide.isAsciiPrintable("\u0021") = true
     * StringAide.isAsciiPrintable("\u007e") = true
     * StringAide.isAsciiPrintable("\u007f") = false
     * StringAide.isAsciiPrintable("Ceki G\u00fclc\u00fc") = false
     * </pre>
     *
     * @param input 要检查的字符序列，可以为 {@code null}
     * @return 当字符序列中的每个字符都在 32（含） 到 126（含）之间时返回 {@code true}
     */
    public static boolean isAsciiPrintable(final CharSequence input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (!CharAide.isAsciiPrintable(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查找 target 在 input 第一次出现的位置索引。
     *
     * <pre>
     * StringAide.indexOf(null, *)          = -1
     * StringAide.indexOf(*, null)          = -1
     * StringAide.indexOf("", "")           = 0
     * StringAide.indexOf("", *)            = -1 (except when * = "")
     * StringAide.indexOf("aabaabaa", "a")  = 0
     * StringAide.indexOf("aabaabaa", "b")  = 2
     * StringAide.indexOf("aabaabaa", "ab") = 1
     * StringAide.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param input 要检查的 CharSequence，可以为 null
     * @param target 要查找的目标 CharSequence，可以为 null
     * @return 索引，当 input 或 target 为 null 时返回 -1
     */
    public static int indexOf(final CharSequence input, final CharSequence target) {
        if (input == null || target == null) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceAide.indexOf(input, target, 0);
    }

    public static int indexOf(final CharSequence input, final CharSequence target, final int start) {
        if (input == null || target == null) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceAide.indexOf(input, target, start);
    }

}
