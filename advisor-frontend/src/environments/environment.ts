// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  api_url: 'https://localhost:4200/',
  google_client_id: '842559739559-44i02i0se3q9a1j06ns081731phou72t.apps.googleusercontent.com',
  google_api_profiles: [
    'profile',
    'email',
    'https://www.googleapis.com/auth/plus.me',
  ].join(' ')
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
