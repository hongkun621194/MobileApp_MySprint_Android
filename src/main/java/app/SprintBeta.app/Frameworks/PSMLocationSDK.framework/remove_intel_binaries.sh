# Copyright © 2017 Pinsight Media. All rights reserved.

# This script is supposed to be shipped with the SDK
# It should be called as part of the app build process (a "Run Script" build phase). See the integration guide for more details.
# This script is here to work around a problem with dynamic frameworks submission into the AppStore:
# http://www.openradar.me/radar?id=6409498411401216
# As of Xcode 8, it's still an issue.

cd "${BUILT_PRODUCTS_DIR}/${FRAMEWORKS_FOLDER_PATH}"

echo "Removing all architectures but the valid ones."
for file in $(find . -type f -perm +111); do
	# Skip non-dynamic libs.
	if ! [[ "$(file "$file")" == *"dynamically linked shared library"* ]]; then
		continue
	fi

	# Get the current file architecture.
	archs="$(lipo -info "${file}" | rev | cut -d ':' -f1 | rev)"
	removed=""
	for arch in $archs; do
		#removing invalid architectures
		if ! [[ "${VALID_ARCHS}" == *"$arch"* ]]; then
			lipo -remove "$arch" -output "$file" "$file" || exit 1
			removed="$removed $arch"
		fi
	done

	if [[ "$removed" != "" ]]; then
		echo "Architectures $removed were removed from $file"
		if [ "${CODE_SIGNING_REQUIRED}" == "YES" ]; then
			echo "Fixing code signing for file ${file}"
			/usr/bin/codesign --force --sign ${EXPANDED_CODE_SIGN_IDENTITY} --preserve-metadata=identifier,entitlements "${file}"
		fi
	fi
done
