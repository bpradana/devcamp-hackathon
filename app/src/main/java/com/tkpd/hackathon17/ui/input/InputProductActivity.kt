package com.tkpd.hackathon17.ui.input

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkpd.hackathon17.data.model.Product
import com.tkpd.hackathon17.databinding.ActivityInputProductBinding
import com.tkpd.hackathon17.utils.Utils.getPictureData
import com.tkpd.hackathon17.viewmodel.ViewModelFactory
import java.util.*

class InputProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputProductBinding
    private lateinit var viewModel: InputProductViewModel
    private lateinit var imageUri: Uri
    private lateinit var listTags: ArrayList<String>
    private lateinit var tagsAdapter: TagsAdapter
    private var isExifExist: Boolean = false

    companion object {
        const val RESULT_CODE_ADD_PRODUCT = 1
        const val EXTRA_RESULT_ADD = "result add"
        const val REQUEST_CODE_CHOOSE_IMAGE = 200
        const val PERMISSION_CODE = 1000
        const val TAG = "InputProductActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Input Product"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[InputProductViewModel::class.java]

        binding.btnChooseImage.setOnClickListener { chooseImage() }
        binding.btnSubmit.setOnClickListener { save() }
        binding.btnAddTag.setOnClickListener { addTag() }
        populateVariant()
    }

    private fun chooseImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            } else{
                selectImage()
            }
        } else {
            selectImage()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_IMAGE) {
            imageUri = data?.data!!
            isExifExist = false
            val exif = getPictureData(this, imageUri)
            val tagsToCheck = arrayOf(
                ExifInterface.TAG_DATETIME,
                ExifInterface.TAG_GPS_LATITUDE,
                ExifInterface.TAG_GPS_LONGITUDE,
                ExifInterface.TAG_EXPOSURE_TIME,
                ExifInterface.TAG_COPYRIGHT,
                ExifInterface.TAG_DEVICE_SETTING_DESCRIPTION,
            )
            val hashMap = HashMap<String, String>()
            for (tag in tagsToCheck)
                exif?.getAttribute(tag)?.let {
                    hashMap[tag] = it
                    Log.d(TAG, "exif image, $tag = $it")
                    isExifExist = true
                }

            Log.d(TAG, "image data = $data")
            binding.ivImage.visibility = View.VISIBLE
            binding.warningImage.visibility = View.GONE
            binding.ivImage.setImageURI(imageUri)
        }
    }

    private fun save() {
        binding.apply {
            val title: String = etTitle.text.toString()
            val price: String = etPrice.text.toString()
            val desc: String = etDesc.text.toString()

            if (title.isEmpty()) {
                etTitle.error = "Please fill in the data!"
                etTitle.requestFocus()
                return
            }
            if (price.isEmpty()) {
                etPrice.error = "Please fill in the data!"
                etPrice.requestFocus()
                return
            }

            if (!::imageUri.isInitialized) {
                warningImage.visibility = View.VISIBLE
                warningImage.requestFocus()
                return
            }

            val id = UUID.randomUUID().toString()
            val product = Product(id, title, price.toFloat(), desc, null, null, isExifExist)
            Log.d(TAG, "product = $product")

            viewModel.addProductToStorage(product, imageUri).apply {
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_RESULT_ADD, this)
                setResult(RESULT_CODE_ADD_PRODUCT, resultIntent)
                finish()
            }
        }
    }

    private fun addTag() {
        binding.apply {
            val tagName = etTag.text.toString()

            if (tagName.isEmpty()) {
                etTag.error = "Please fill in the data!"
                etTag.requestFocus()
                return
            }

            if (tagName.isNotEmpty()) {
                listTags.add(tagName).apply {
                    tagsAdapter.notifyDataSetChanged()
                    etTag.setText("")
                }
            }
        }
    }

    private fun populateVariant() {
        listTags = ArrayList(0)
        tagsAdapter = TagsAdapter(listTags)
        binding.apply {
            rvTags.layoutManager = LinearLayoutManager(this@InputProductActivity)
            rvTags.setHasFixedSize(true)
            rvTags.adapter = tagsAdapter
        }

        tagsAdapter.setOnItemClickCallback(object : TagsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) { }
            override fun onItemDelete(data: String) {
                listTags.remove(data)
                tagsAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}