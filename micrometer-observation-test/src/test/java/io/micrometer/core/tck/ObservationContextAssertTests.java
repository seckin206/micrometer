/*
 * Copyright 2021 VMware, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.tck;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.junit.jupiter.api.Test;

import static io.micrometer.core.tck.ObservationContextAssert.assertThat;
import static org.assertj.core.api.BDDAssertions.thenNoException;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class ObservationContextAssertTests {

    ObservationRegistry registry = ObservationRegistry.create();

    Observation.Context context = new Observation.Context();

    @Test
    void should_not_throw_exception_when_name_correct() {
        context.setName("foo");

        thenNoException().isThrownBy(() -> assertThat(context).hasNameEqualTo("foo"));
    }

    @Test
    void should_throw_exception_when_name_incorrect() {
        context.setName("foo");

        thenThrownBy(() -> assertThat(context).hasNameEqualTo("bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_name_incorrect() {
        context.setName("bar");

        thenNoException().isThrownBy(() -> assertThat(context).doesNotHaveNameEqualTo("foo"));
    }

    @Test
    void should_throw_exception_when_name_correct() {
        context.setName("foo");

        thenThrownBy(() -> assertThat(context).doesNotHaveNameEqualTo("foo")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_contextual_name_correct() {
        context.setContextualName("foo");

        thenNoException().isThrownBy(() -> assertThat(context).hasContextualNameEqualTo("foo"));
    }

    @Test
    void should_throw_exception_when_contextual_name_incorrect() {
        context.setContextualName("foo");

        thenThrownBy(() -> assertThat(context).hasContextualNameEqualTo("bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_contextual_name_incorrect() {
        context.setContextualName("bar");

        thenNoException().isThrownBy(() -> assertThat(context).doesNotHaveContextualNameEqualTo("foo"));
    }

    @Test
    void should_throw_exception_when_contextual_name_correct() {
        context.setContextualName("foo");

        thenThrownBy(() -> assertThat(context).doesNotHaveContextualNameEqualTo("foo")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_name_ignore_case_correct() {
        context.setName("FOO");

        thenNoException().isThrownBy(() -> assertThat(context).hasNameEqualToIgnoringCase("foo"));
    }

    @Test
    void should_throw_exception_when_name_ignore_case_incorrect() {
        context.setName("foo");

        thenThrownBy(() -> assertThat(context).hasNameEqualToIgnoringCase("bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_name_ignore_case_incorrect() {
        context.setName("bar");

        thenNoException().isThrownBy(() -> assertThat(context).doesNotHaveNameEqualToIgnoringCase("foo"));
    }

    @Test
    void should_throw_exception_when_name_ignore_case_correct() {
        context.setName("BAR");

        thenThrownBy(() -> assertThat(context).doesNotHaveNameEqualToIgnoringCase("bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_contextual_name_ignore_case_correct() {
        context.setContextualName("FOO");

        thenNoException().isThrownBy(() -> assertThat(context).hasContextualNameEqualToIgnoringCase("foo"));
    }

    @Test
    void should_throw_exception_when_contextual_name_ignore_case_incorrect() {
        context.setContextualName("foo");

        thenThrownBy(() -> assertThat(context).hasContextualNameEqualToIgnoringCase("bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_contextual_name_ignore_case_incorrect() {
        context.setContextualName("bar");

        thenNoException().isThrownBy(() -> assertThat(context).doesNotHaveContextualNameEqualToIgnoringCase("foo"));
    }

    @Test
    void should_throw_exception_when_contextual_name_ignore_case_correct() {
        context.setContextualName("BAR");

        thenThrownBy(() -> assertThat(context).doesNotHaveContextualNameEqualToIgnoringCase("bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_low_cardinality_tag_exists() {
        Observation observation = Observation.start("foo", context, registry);
        observation.lowCardinalityTag("foo", "bar");

        thenNoException().isThrownBy(() -> assertThat(context).hasLowCardinalityTag("foo", "bar"));
    }

    @Test
    void should_throw_exception_when_low_cardinality_tag_missing() {
        Observation observation = Observation.start("foo", context, registry);
        observation.lowCardinalityTag("foo", "bar");

        thenThrownBy(() -> assertThat(context).hasLowCardinalityTag("foo", "baz")).isInstanceOf(AssertionError.class);
        thenThrownBy(() -> assertThat(context).hasLowCardinalityTagWithKey("bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_high_cardinality_tag_exists() {
        Observation observation = Observation.start("foo", context, registry);
        observation.highCardinalityTag("foo", "bar");

        thenNoException().isThrownBy(() -> assertThat(context).hasHighCardinalityTag("foo", "bar"));
    }

    @Test
    void should_throw_exception_when_high_cardinality_tag_missing() {
        Observation observation = Observation.start("foo", context, registry);
        observation.highCardinalityTag("foo", "bar");

        thenThrownBy(() -> assertThat(context).hasHighCardinalityTag("foo", "baz")).isInstanceOf(AssertionError.class);
        thenThrownBy(() -> assertThat(context).hasHighCardinalityTagWithKey("bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_high_cardinality_tag_present() {
        thenNoException().isThrownBy(() -> assertThat(context).doesNotHaveHighCardinalityTag("foo", "bar"));
    }

    @Test
    void should_throw_exception_when_high_cardinality_tag_present() {
        Observation observation = Observation.start("foo", context, registry);
        observation.highCardinalityTag("foo", "bar");

        thenThrownBy(() -> assertThat(context).doesNotHaveHighCardinalityTag("foo", "bar")).isInstanceOf(AssertionError.class);
        thenThrownBy(() -> assertThat(context).doesNotHaveHighCardinalityTagWithKey("foo")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_low_cardinality_tag_missing() {
        thenNoException().isThrownBy(() -> assertThat(context).doesNotHaveLowCardinalityTag("foo", "bar"));
    }

    @Test
    void should_throw_exception_when_low_cardinality_tag_present() {
        Observation observation = Observation.start("foo", context, registry);
        observation.lowCardinalityTag("foo", "bar");

        thenThrownBy(() -> assertThat(context).doesNotHaveLowCardinalityTag("foo", "bar")).isInstanceOf(AssertionError.class);
        thenThrownBy(() -> assertThat(context).doesNotHaveLowCardinalityTagWithKey("foo")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_any_tags_exist() {
        Observation observation = Observation.start("foo", context, registry);
        observation.highCardinalityTag("foo", "bar");

        thenNoException().isThrownBy(() -> assertThat(context).hasAnyTags());
    }

    @Test
    void should_throw_exception_when_no_tags_present() {
        thenThrownBy(() -> assertThat(context).hasAnyTags()).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_no_tags_exist() {
        thenNoException().isThrownBy(() -> assertThat(context).hasNoTags());
    }

    @Test
    void should_throw_exception_when_tags_present() {
        Observation observation = Observation.start("foo", context, registry);
        observation.highCardinalityTag("foo", "bar");

        thenThrownBy(() -> assertThat(context).hasNoTags()).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_throw_exception_when_map_entry_missing() {
        context.put("foo", "bar");

        thenThrownBy(() -> assertThat(context).hasMapEntry("foo", "baz")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_map_entry_present() {
        context.put("foo", "bar");

        thenNoException().isThrownBy(() -> assertThat(context).hasMapEntry("foo", "bar"));
    }

    @Test
    void should_throw_exception_when_map_entry_present() {
        context.put("foo", "bar");

        thenThrownBy(() -> assertThat(context).doesNotHaveMapEntry("foo", "bar")).isInstanceOf(AssertionError.class);
    }

    @Test
    void should_not_throw_exception_when_map_entry_missing() {
        thenNoException().isThrownBy(() -> assertThat(context).doesNotHaveMapEntry("foo", "bar"));
    }

    @Test
    void should_jump_to_and_back_from_throwable_assert() {
        context.setName("foo").setError(new RuntimeException("bar"));

        thenNoException().isThrownBy(() -> assertThat(context)
                .hasNameEqualTo("foo")
                .thenThrowable()
                .hasMessage("bar")
                .backToContext()
                .hasNameEqualTo("foo"));
    }

}
