<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>

<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Registration</title>
<link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.3.1/minty/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.css">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />
<link href="resources/css/tailwind.output.css" rel="stylesheet"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"
	integrity="sha512-wnea99uKIC3TJF7v4eKk4Y+lMz2Mklv18+r4na2Gn1abDRPPOeef95xTzdwGD9e6zXJBteMIhZ1+68QC5byJZw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
<script type="text/javascript" src="resources/js/init-alpine.js"></script>


</head>

<body>

	<form action="RegisterURL" method="POST"
		id="register" modelAttribute="registerForm"
		class="w-full md:w-1/2 p-5 mx-auto bg-white border-2 border-gray-400 shadow rounded-lg m-5 ">
		<h1
			class="text-xl font-bold text-gray-1200 dark:text-gray-200 header_tag">
			Create account</h1>
			
		<div class="flex flex-row w-full ">
			<label class=" mt-2 mt-4 mb-2 text-md font-semibold">
			 <span class="text-gray-700 dark:text-gray-400">Upload Image</span><br />
						<input type="file" id="image" class="myimage" name="img"
						onchange="readURL(this);"
						class="self-start w-full p-1 mt-1 text-md focus:outline-none focus:border-none">
						
						<#-- <input type="hidden" value="" name="oldImg" /> -->
					</label>
			
					<img src="" id="blah"
						class="mx-auto h-170" />


		</div>

		

		<div class="flex flex-col md:flex-row gap-4 md:gap-8">
			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">First Name</span> <input
					type="text" name="firstName" id="firstname"
					value=""
					class=" w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="Jane" /> <span id="fname_error"></span>
				</label>

			</div>


			<div class="w-full">
				<label class="block mt-2  mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Last Name</span> <input
					type="text" name="lastName" id="lastname" value=""
					class="w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="Doe" /> <span id="lname_error"></span>

				</label>

			</div>
		</div>

		<div class="flex  flex-col md:flex-row gap-8">
			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Email</span> <input
					type="text" name="email" id="email" value=""
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="JaneDoeXX@gmail.com" /> <span id="email_error"></span>
					<span id="EmailError"></span>


				</label>
			</div>

			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Contact</span> <input
					type="number" name="contactNo" id="phone" value=""
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="xxxxxxxxxx" /> <span id="number_error"></span>


				</label>


			</div>
		</div>

		<div class="flex  flex-col md:flex-row gap-8">
			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Password</span> <input
					type="password" name="password" id="password"
					value=""
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="***************" /> <span id="password_error"></span>

				</label>
			</div>

			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Confirm Password</span> <input
					type="password" name="confirmPassword" id="Cpassword"
					value=""
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="***************" /> <span id="Cpassword_error"></span>

				</label>
			</div>
		</div>

		<div class="flex  flex-col md:flex-row gap-8">
			<div class="w-full">
				<label for="" class="block mt-6 mb-2 text-md font-semibold">
					<span class="text-gray-700 mr-8 dark:text-gray-400">Gender</span><br />
					<input type="radio" id="gender" class="mt-3" name="gender"
					value="male" > <span
					class="text-gray-700 mr-4 dark:text-gray-400"> Male</span> <input
					type="radio" name="gender" value="Female"> <span
					class="text-gray-700 mr-2  dark:text-gray-400">Female</span> <span
					class="block italic text-red-500" id="genderSpan"></span> <span
					id="gender_error"></span>


				</label>
			</div>
			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Birth Date</span> <input
					type="date" name="birthDate" id="bdate" value=""
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" />

					<span id="bDate_error"></span>

				</label>
			</div>
		</div>


		<div class="w-full">
			<label class="block mt-2 mt-4 mb-2 text-md font-semibold"> <span
				class="text-gray-700 dark:text-gray-400">Known Language</span><br />
				<input type="checkbox" class="mt-2 mr-3" name="languages"
				value="Gujarati"> <label
				class="text-gray-700 dark:text-gray-400">Gujarati</label><br /> <input
				type="checkbox" class="mt-2 mr-3" name="languages" value="Hindi"> <label
				class="text-gray-700 dark:text-gray-400">Hindi</label><br /> <input
				type="checkbox" class="mt-2 mr-3" name="languages" value="English"> <label
				class="text-gray-700 dark:text-gray-400">English</label><br /> <span
				id="lang_error"></span>

			</label>

		</div>  
				<div id="main-container">
					<span
						class="text-gray-700 dark:text-gray-400 text-lg font-semibold">Address</span>
						<div class="panel card container-item mb-2">
							<div class="panel-body">
								<div class="panel-body pl-2 pr-2 pb-2">

									<input type="hidden" name="id[]"
										value="">
										
										
										<label class="block mt-2 text-md font-semibold"><span
										class="text-gray-700 dark:text-gray-400">Street Address</span>
										<input type="text" name="addressLine[]" id="address"
										value=""
										class="w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" />
										<span id="aLine_error"></span> </label>
									<div class="flex flex-row gap-8">
										<div class="w-full">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">City
												<select id="city" name="city[]"
												class="w-full text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"">
													<option value="select">Select your city</option>
													<option value="Ahmedabad">Ahmedabad</option>
													<option value="Rajkot">Rajkot</option>
													<option value="Surat">Surat</option>
													<option value="Gandhinagar">Gandhinagar</option>
											</select> <span id="city_error"></span>
											</label>
										</div>

										<div class="w-full">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">State
												<select id="state" name="state[]"
												class="w-full text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"">
													<option value="select">Select your state</option>
													<option value="Gujarat">Gujarat</option>
													<option value="Rajsthan">Rajsthan</option>
													<option value="Bihar">Bihar</option>
													<option value="Punjab">Punjab</option>
											</select> <span id="state_error"></span>
											</label>
										</div>
									</div>

									<div class="flex flex-row gap-8">
										<div class="w-full">
											<label class="block mt-2 mt-4 text-md font-semibold">
												<span class="text-gray-700 dark:text-gray-400">Postal
													Code</span> <input type="number" name="pin[]" id="pin"
												value=""
												class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
												placeholder="xxxxxx" /> <span id="pin_error"></span>
											</label>
										</div>
										<div class="w-full grid content-center justify-center">
											<div class="mt-8">
												<a href="javascript:void(0)"
													class="remove-item bg-red-500 text-white px-4 py-2 rounded-md">Remove</a>
											</div>
										</div>

									</div>
								</div>

							</div>

						</div>
					
				</div>

			
			

				<div class="grid w-1/2 justify-start">
					<a class="px-4 py-2 bg-green-400 text-white rounded " id="add-more"
						href="javascript:;" role="button"><i class="fa fa-plus"></i>
						Add more address</a>


				</div>

		
				<input type="submit" value="Register" id="registerButton"
			class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">


		
		<hr class="my-8" />

		<p class="mt-4">
			<a
				class="text-sm font-medium text-purple-600 dark:text-purple-400 hover:underline dLogin"
				href="index"> Already have an account? Login </a>
		</p>


	</form>
	
	<script type="text/javascript">
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result).width(200).height(
							200);
				};

				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.js"></script>
	<script src="https://cdn.ckeditor.com/4.5.1/standard/ckeditor.js"></script>
	
	<script type="text/javascript" src="resources/js/cloneData.js"></script>
	<script type="text/javascript" src="resources/js/validation.js"></script>

	

	
</body>
</html>
