package vision.genesis.clientapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;
import vision.genesis.clientapp.BuildConfig;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class ImageUtils
{
	public static final int CAMERA_REQUEST_CODE = 94;

	public static final int GALLERY_REQUEST_CODE = 25;

	public static final int CROP_REQUEST_CODE = 128;

	public static final int AVATAR_WIDTH = 500;

	public static String getImageUri(String imageId) {
		String baseUrl = BuildConfig.FLAVOR.equals("tournament")
				? BuildConfig.TOURNAMENT_API_ADDRESS
				: BuildConfig.API_ADDRESS;
		return (baseUrl + "/api/files/" + imageId);
	}

	public static boolean saveImageToFile(Context context, Bitmap image, String imageUri) {
		FileOutputStream out = null;
		try {
			File outFile = new File(Uri.parse(imageUri).getPath());

			out = new FileOutputStream(outFile);
			Bitmap scaledImage = Bitmap.createScaledBitmap(image, AVATAR_WIDTH, AVATAR_WIDTH, false);
			scaledImage.compress(Bitmap.CompressFormat.JPEG, 85, out);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (out != null) {
					out.close();
					return true;
				}
				else {
					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public static void copyFiles(File src, File dst) throws IOException {
		try (InputStream in = new FileInputStream(src)) {
			try (OutputStream out = new FileOutputStream(dst)) {
				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			}
		}
	}

	public void openCameraFrom(Activity activity) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
			activity.startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
		}
	}

	public void openCameraFrom(Fragment fragment) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
			fragment.startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
		}
	}

	public void openGalleryFrom(Activity activity) {
		Intent openGalleryIntent = new Intent(
				Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		activity.startActivityForResult(openGalleryIntent, GALLERY_REQUEST_CODE);
	}

	public void openCameraFrom(Fragment fragment, @NonNull File imageFile) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					FileProvider.getUriForFile(fragment.getContext(), BuildConfig.APPLICATION_ID + ".provider", imageFile));
			takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			fragment.startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
		}
	}

	public void openGalleryFrom(Fragment fragment) {
		Intent openGalleryIntent = new Intent(
				Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		fragment.startActivityForResult(openGalleryIntent, GALLERY_REQUEST_CODE);
	}

	public static boolean deleteTempFile(File tempFile) {
		return tempFile.delete();
	}

	public String getImagePath(Context context, Uri data) {
		Timber.d("Getting image path from %s", data);
		String[] filePathColumn = {MediaStore.Images.Media.DATA};

		Cursor cursor = context.getContentResolver().query(data,
				filePathColumn, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			return picturePath;
		}
		else {
			throw new RuntimeException("Could not find specified uri: " + data);
		}
	}

	public File createImageFile(String name) throws IOException {
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		return File.createTempFile(name, ".jpg", storageDir);
	}

	public File createImageFile() throws IOException {
		String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
		String imageFileName = "gv_" + timestamp;
		return createImageFile(imageFileName);
	}
}
