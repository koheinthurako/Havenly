<template>
  <div class="container" style="margin-top: 200px;">
    <v-row>
      <v-col cols="12">
        <v-menu v-model="menu" :close-on-content-click="false" offset-y :activator="activator"
          transition="scale-transition" max-height="160">
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-if="!searchActive" icon @click="showSearchBar" v-bind="attrs" v-on="on">
              <v-icon>mdi-magnify</v-icon>
            </v-btn>
          </template>
          <template v-slot:default>
            <v-overlay :value="searchActive" absolute>
              <v-container class="d-flex justify-center align-center">
                <v-text-field variant="outlined" v-model="search" label="Search posts by name"
                  append-inner-icon="mdi-magnify" clearable @input="onSearch" ref="searchField"></v-text-field>
              </v-container>
            </v-overlay>
          </template>
        </v-menu>
        <v-list v-if="filteredTitles.length" class="p-0">
          <h4 class="ms-3 mt-2" style="color: #e86f52;">Available posts</h4>
          <v-list-item v-for="post in filteredTitles" :key="post.id" @click="handleItemClick(post)"
            style="border-bottom: 1px solid #000;">
            <v-list-item-title>
              <v-chip v-if="post.type === 'Sell'" prepend-icon="mdi-checkbox-marked-circle" size="small" rounded-pill
                color="red" variant="flat" class="me-1">
                {{ post.type }}
              </v-chip>
              <v-chip v-else prepend-icon="mdi-checkbox-marked-circle" size="small" rounded-pill color="green"
                variant="flat" class="me-1">
                {{ post.type }}
              </v-chip>
              {{ post.title }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
        <v-alert v-else-if="search" type="warning" class="ma-0">
          No post available
        </v-alert>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menu: false,
      search: '',
      searchActive: false,
      filteredTitles: [], // Initialize with your data
    };
  },
  methods: {
    showSearchBar() {
      this.searchActive = true;
      this.$nextTick(() => {
        if (this.$refs.searchField) {
          this.$refs.searchField.focus();
        }
      });
    },
    onSearch() {
      // Your search logic here
    },
    handleItemClick(post) {
      // Handle item click
      alert(`Selected post: ${post.title}`);
      this.searchActive = false; // Hide search bar after selecting an item
    },
  },
};
</script>

<style scoped>
.v-overlay__content {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
