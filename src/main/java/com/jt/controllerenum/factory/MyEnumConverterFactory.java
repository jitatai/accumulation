/*
 * Copyright 2002-2020 the original author or authors.
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

package com.jt.controllerenum.factory;

import com.jt.controllerenum.enums.MyJsonCreator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;


@SuppressWarnings({"rawtypes", "unchecked"})
public final class MyEnumConverterFactory implements ConverterFactory<String, Enum> {

	@Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToEnum(targetType);
	}


	private static class StringToEnum<T extends Enum> implements Converter<String, T> {

		private final Class<T> enumType;

		StringToEnum(Class<T> enumType) {
			this.enumType = enumType;
		}

		@Override
		@Nullable
		public T convert(String source) {
			if (source.isEmpty()) {
				return null;
			}
			for (T enumObject : enumType.getEnumConstants()) {
				Class<? extends Enum> enumObjectClass = enumObject.getClass();
				Field[] declaredFields = enumObjectClass.getDeclaredFields();
				for (Field declaredField : declaredFields) {
					if (declaredField.isAnnotationPresent(MyJsonCreator.class)){
						declaredField.setAccessible(true);
						try {
							if (source.equals(declaredField.get(enumObject))){
								return enumObject;
							}
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return (T) Enum.valueOf(this.enumType,source.trim());
		}
	}

}
