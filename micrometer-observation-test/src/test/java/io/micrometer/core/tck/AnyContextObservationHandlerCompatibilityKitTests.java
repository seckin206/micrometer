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
import io.micrometer.observation.ObservationHandler;

class AnyContextObservationHandlerCompatibilityKitTests extends AnyContextObservationHandlerCompatibilityKit {

    @Override
    public ObservationHandler<Observation.Context> handler() {
        return new ObservationHandler<Observation.Context>() {
            @Override
            public void onStart(Observation.Context context) {
            }

            @Override
            public void onError(Observation.Context context) {
            }

            @Override
            public void onScopeOpened(Observation.Context context) {
            }

            @Override
            public void onStop(Observation.Context context) {
            }

            @Override
            public boolean supportsContext(Observation.Context context) {
                return true;
            }
        };
    }
}
