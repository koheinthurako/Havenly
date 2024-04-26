<!-- <template>
  <div style="margin-top: 7%;"></div>
  <div>
    <div class="container mt-4">
      <div class="row">
        <div class="col-md-4">
          <div class="form-group">
            <label for="selectOption">Select an option:</label>
            <select class="form-control" id="selectOption" v-model="selectedOption">
              <option value="option1">Option 1</option>
              <option value="option2">Option 2</option>
              <option value="option3">Option 3</option>
            </select>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-4" :class="{ 'hidden': selectedOption !== 'option1' }" transition="slide">
          <div class="form-group">
            <label for="input1Option1">Input 1 for option 1:</label>
            <input type="text" class="form-control" id="input1Option1" v-model="option1Input1">
          </div>
          <div class="form-group">
            <label for="input2Option1">Input 2 for option 1:</label>
            <input type="text" class="form-control" id="input2Option1" v-model="option1Input2">
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-4" :class="{ 'hidden': selectedOption !== 'option2' }" transition="slide">
          <div class="form-group">
            <label for="input1Option2">Input 1 for option 2:</label>
            <input type="text" class="form-control" id="input1Option2" v-model="option2Input1">
          </div>
        </div>
      </div>


      <div class="row">
        <div class="col-md-4" :class="{ 'hidden': selectedOption !== 'option3' }" transition="slide">
          <div class="form-group">
            <label for="input1Option3">Input 1 for option 3:</label>
            <input type="text" class="form-control" id="input1Option3" v-model="option3Input1">
          </div>
          <div class="form-group">
            <label for="input2Option3">Input 2 for option 3:</label>
            <input type="text" class="form-control" id="input2Option3" v-model="option3Input2">
          </div>
          <div class="form-group">
            <label for="input3Option3">Input 3 for option 3:</label>
            <input type="text" class="form-control" id="input3Option3" v-model="option3Input3">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      selectedOption: 'option1',
      option1Input1: '',
      option1Input2: '',
      option2Input1: '',
      option3Input1: '',
      option3Input2: '',
      option3Input3: ''
    };
  }
};
</script>

<style>
.hidden {
  position: absolute;
  opacity: 0;
  pointer-events: none;
  animation: slideOut 0.6s both;
}

@keyframes slideOut {
  0% {
    opacity: 1;
    transform: translateX(0);
  }

  50% {
    opacity: 1;
    transform: translateX(-100px);
  }

  100% {
    opacity: 0;
    transform: translateX(-200px);
  }
}
</style> -->


<template>
  <div style="margin-top: 8%;"></div>
  <div class="ms-5">
    <label class="file-input">
      <input type="file" accept="image/*" @change="handleFileSelect" multiple>
      <span class="mdi mdi-plus"></span>
      <span>Add Image</span>
    </label>
    <div v-if="selectedImages">
      <h2>Selected Images:</h2>
      <div class="image-row">
        <img v-for="(image, index) in selectedImages" :key="index" :src="image.url" :alt="image.name"
          class="image-item">
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  name: 'FileInput',
  setup() {
    const selectedImages = ref([]);

    const handleFileSelect = (event) => {
      const files = event.target.files;
      if (files) {
        const selected = Array.from(files);
        selectedImages.value = selected.map(file => ({
          name: file.name,
          url: URL.createObjectURL(file)
        }));
      }
    };

    return {
      selectedImages,
      handleFileSelect
    };
  }
};
</script>

<style>
.file-input {
  display: inline-block;
  position: relative;
  overflow: hidden;
  padding: 10px 15px;
  cursor: pointer;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}


.file-input .v-icon {
  font-size: 24px;
  margin-right: 5px;
}


.file-input input[type="file"] {
  display: none;
}


.image-row {
  display: flex;
  flex-wrap: wrap;
}

.image-item {
  width: 300px;
  margin-right: 10px;
  margin-bottom: 10px;
}
</style>
