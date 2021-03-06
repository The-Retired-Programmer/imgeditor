/*
 * Copyright 2019 richard linsdale.
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
package uk.theretiredprogrammer.imageditor;

/**
 *
 * The basic ChangeListener Interface
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 * @param <T> the class of the parameter to be passed on change detection
 */
public interface ChangeListener<T> {

    /**
     * Called when change is detected.
     *
     * @param t the parameter passed
     */
    public void changedDetected(T t);
}
