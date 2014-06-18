/*
 * Copyright (C) 2014 Naver Business Platform Corp.
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
package kr.androy.volleyext.cache.universalimageloader.memory.impl;

import kr.androy.volleyext.cache.universalimageloader.memory.UniversalImageCache;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;

/**
 * A wrapper class for {@link UsingFreqLimitedMemoryCache}
 * 
 * @see UniversalImageCache
 * @see UsingFreqLimitedMemoryCache
 * 
 */
public class UniversalUsingFreqLimitedMemoryCache extends UniversalImageCache {
	/**
	 * @param sizeLimit
	 *            Maximum size for cache (in bytes)
	 */
	public UniversalUsingFreqLimitedMemoryCache(int sizeLimit) {
		super(new UsingFreqLimitedMemoryCache(sizeLimit));
	}
}
