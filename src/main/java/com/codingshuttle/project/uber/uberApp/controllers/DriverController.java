package com.codingshuttle.project.uber.uberApp.controllers;

import com.codingshuttle.project.uber.uberApp.dto.*;
import com.codingshuttle.project.uber.uberApp.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideId , @RequestBody RideStartDto rideStartDto){

        return ResponseEntity.ok(driverService.startRide(rideId , rideStartDto.getOtp() ));
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDto> endRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/rateRider")
    public ResponseEntity<RiderDto> rateRider(@RequestBody RatingDto rateDto){
        return ResponseEntity.ok(driverService.rateRider(rateDto.getRideId(), rateDto.getRating()));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<DriverDto> getMyProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0", required = false) Integer pageOffset ,
                                                       @RequestParam(defaultValue = "10" , required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(
                pageOffset,
                pageSize,
                Sort.by(Sort.Direction.DESC , "createdTime" , "id"));
        return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
    }

    @PostMapping("/rateRider/{rideId}/{rating}")
    public ResponseEntity<RiderDto> rateRider(@PathVariable Long rideId , @PathVariable Integer rating){
        return ResponseEntity.ok(driverService.rateRider(rideId,rating));
    }
}
