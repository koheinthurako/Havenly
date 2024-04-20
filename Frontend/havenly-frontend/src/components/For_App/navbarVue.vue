<template>
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
          <a class="navbar-brand" href="#"><h2>Havenly</h2></a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
              <li class="nav-item">
                <router-link to="/home" class="nav-link">Home</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/home" class="nav-link">Category</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/home" class="nav-link">Contact</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/home" class="nav-link">Account</router-link>
              </li>
            </ul>
          </div>

        </div>
      </nav>
</template>


<script>
export default {
    name: 'navbarVue',

    data:()=>({

    }),
}

</script>