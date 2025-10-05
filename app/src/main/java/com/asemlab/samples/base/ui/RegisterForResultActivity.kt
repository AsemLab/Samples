package com.asemlab.samples.base.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.base.R
import com.asemlab.samples.base.databinding.ActivityRegisterForResultBinding


class RegisterForResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterForResultBinding
    private var name: String? = null

    private val getContactLauncher =
        registerForActivityResult(ActivityResultContracts.PickContact()) {
            if (it != null) {
                val cursor = contentResolver.query(
                    it,
                    arrayOf(ContactsContract.Contacts.DISPLAY_NAME),
                    null,
                    null,
                    null
                )
                cursor?.moveToFirst()
                try {
                    name =
                        cursor?.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                } catch (e: Exception) {
                    Log.e("MainActivity", e.message!!)
                } finally {
                    cursor?.close()
                }

                binding.name.text = name
            } else {
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
            }
        }

    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, it))
                } else {
                    MediaStore.Images.Media.getBitmap(contentResolver, it)
                }
                val roundImage = RoundedBitmapDrawableFactory.create(
                    resources,
                    bitmap
                ).apply {
                    isCircular = true
                    cornerRadius = 120f
                }

                binding.img.setImageDrawable(roundImage)
            } else
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
        }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            it?.let {
                if (!it)
                    Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val photoPickerLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                Toast.makeText(this, "Selected URI: $it", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No media selected", Toast.LENGTH_SHORT).show()
            }
        }

    // TODO Pick multiple images/videos
    private val pickMultipleMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            if (uris.isNotEmpty()) {
                Toast.makeText(this, "Number of items selected: ${uris.size}", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "No media selected", Toast.LENGTH_SHORT).show()
            }
        }


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_for_result)

        with(binding) {
            pickContact.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (contactPermissionNotGranted()) {
                        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
                    } else {
                        getContactLauncher.launch(null)
                    }
                } else {
                    getContactLauncher.launch(null)
                }
            }

            pickImage.setOnClickListener {
                imagePickerLauncher.launch("image/*")
            }

            // TODO Use PhotoPicker
            newPickImage.setOnClickListener {
                photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
//                photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
                // Use with desired file type
//                photoPickerLauncher.launch(PickVisualMediaRequest(PickVisualMedia.SingleMimeType("image/gif)))
            }

            pickVideo.setOnClickListener {
                photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
            }

            pickMultiple.setOnClickListener {
                pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun contactPermissionNotGranted(): Boolean {
        return checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
    }

}